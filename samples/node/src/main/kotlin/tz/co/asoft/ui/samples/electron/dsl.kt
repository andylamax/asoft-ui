package tz.co.asoft.ui.samples.electron

import kotlinx.coroutines.*
import kotlinx.html.TagConsumer
import kotlinx.html.stream.createHTML
import kotlin.coroutines.CoroutineContext

external interface Process {
    fun exit(code: Int)
}

external val process: Process

class ExpressServer(val app: App) : CoroutineScope {
    protected val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    private var server: Server? = null

    private val gets = mutableMapOf<String, suspend (Request, Response) -> Unit>()
    private val onStarts = mutableListOf<() -> Unit>()
    private val closes = mutableListOf<() -> Unit>()

    fun onStart(callback: () -> Unit) {
        onStarts.add(callback)
    }

    fun get(path: String, callback: suspend (req: Request, res: Response) -> Unit) {
        gets[path] = callback
    }

    fun listen(port: Int, callback: () -> Unit) {
        gets.forEach { app.get(it.key) { req, res -> launch { it.value(req, res) } } }
        server = app.listen(port) {
            onStarts.forEach { it.invoke() }
            callback()
        }
    }

    fun onClose(callback: () -> Unit) {
        closes.add(callback)
    }

    fun close() {
        cancel()
        closes.forEach { it() }
        server?.close { }

        process.exit(0)
    }
}

fun Response.html(builder: TagConsumer<String>.() -> Unit) {
    send(createHTML().apply(builder).finalize())
}

fun expressServer() = ExpressServer(express())

fun express(port: Int, config: ExpressServer.() -> Unit) = expressServer().apply(config).listen(port) {}