package index

import app.app
import event.EnvironmentImpl
import event.EventLoopImpl
import kotlinext.js.require
import kotlinext.js.requireAll
import react.dom.render
import kotlin.browser.document

fun main(args: Array<String>) {
    requireAll(require.context("src", true, js("/\\.css$/")))
    val eventLoop = EventLoopImpl()
    val environment = EnvironmentImpl()
    render(document.getElementById("root")) {
        app(eventLoop, environment)
    }
}
