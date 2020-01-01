package tz.co.asoft.ui.samples.electron

external interface App {
    fun get(path: String, callback: (req: Request, res: Response) -> Unit)
    fun listen(port: Int, callback: () -> Unit = definedExternally): Server
}

external interface Server {
    fun close(callback: () -> Unit)
}

external interface Request {

}

external interface Response {
    fun send(msg: String)
    fun json(obj: Any)
}

@JsModule("express")
external fun express(): App