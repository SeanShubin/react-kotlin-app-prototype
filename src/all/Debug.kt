package all

import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.dom.*

fun RBuilder.debug(sendEvent:(MyEvent)->Unit, exception:Exception) {
    h1 {
        +"Exception"
    }
    pre {
       +JSON.stringify(exception)
    }
    p {
        a(href = "#") {
            +"Foo"
            attrs {
                onClickFunction = {
                    sendEvent(LoadFooRequest)
                }
            }
        }
    }
}
