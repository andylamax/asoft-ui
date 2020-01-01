package tz.co.asoft.ui.samples

import com.soywiz.klock.DateTime
import kotlinx.coroutines.*
import kotlinx.html.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import tz.co.asoft.auth.User
import tz.co.asoft.auth.tools.klock.asFormatedTimeOnly
import tz.co.asoft.ui.samples.electron.Response
import tz.co.asoft.ui.samples.electron.express
import tz.co.asoft.ui.samples.electron.html

const val port = 3000

tailrec suspend fun displayTime() {
    delay(1000)
    println(DateTime.nowUnixLong().asFormatedTimeOnly())
    displayTime()
}

fun main() = express(port) {
    onStart {
        launch {
            displayTime()
        }
    }

    get("/") { _, res ->
        res.send("Tsup Hommie")
    }

    get("/json") { _, res ->
        res.sendUsers()
    }

    get("/off") { _, res ->
        res.send("Shutting down")
        close()
    }

    get("/html") { _, res ->
        res.html {
            head { title { +"HTML Title" } }
            body {
                h1 { +"Big Heading 1" }
                div("normal bold") {
                    onClick = "sayHi()"
                    +"This is a normal Diver"
                }
            }
        }
    }

    onClose {
        println("By By now")
    }
}

private suspend fun Response.sendUsers() {
    val users = mutableListOf<User>().apply {
        repeat(120) { add(User.fake.apply { id = size.toLong(); uid = "$id" }) }
    }
    delay(3000)
    val usersJson = Json.stringify(User.serializer().list, users)
    json(JSON.parse(usersJson))
}