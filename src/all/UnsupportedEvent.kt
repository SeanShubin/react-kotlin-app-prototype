package all

import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.dom.a
import react.dom.h1
import react.dom.p

fun RBuilder.unsupportedEvent(sendEvent: (MyEvent) -> Unit, unsupportedEvent: UnsupportedEvent) {
    h1 {
        +"Unsupported Event"
    }
    p {
        +unsupportedEvent.eventString
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
