package index

import all.*
import app.app
import kotlinext.js.require
import kotlinext.js.requireAll
import org.w3c.dom.Storage
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    requireAll(require.context("src", true, js("/\\.css$/")))
    val eventLoop: EventLoop = EventLoopImpl()
    val storage: Storage = window.localStorage
    val api: Api = StorageApi(storage)
    val environment: Environment = EnvironmentImpl(api)
    render(document.getElementById("root")) {
        app(eventLoop, environment)
    }
}
