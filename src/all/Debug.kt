package all

import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.dom.a
import react.dom.h1
import react.dom.p
import react.dom.pre

fun RBuilder.debug(sendEvent: (MyEvent) -> Unit, exception: Exception) {
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
