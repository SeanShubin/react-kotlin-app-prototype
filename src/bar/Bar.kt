package bar

import event.BarStringChangeRequest
import event.MyEvent
import event.NavFooRequest
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import state.Bar
import react.RBuilder
import react.dom.a
import react.dom.h1
import react.dom.input
import react.dom.p

fun RBuilder.bar(sendEvent:(MyEvent)->Unit, bar: Bar) {
    h1 {
        +"Bar"
    }
    input {
        attrs {
            value = bar.barString
            onChangeFunction = { event ->
                val target = event.target as HTMLInputElement
                sendEvent(BarStringChangeRequest(target.value))
            }
        }
    }
    p {
        a(href = "#") {
            +"Foo"
            attrs {
                onClickFunction = {
                    sendEvent(NavFooRequest)
                }
            }
        }
    }
}
