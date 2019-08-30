package bar

import event.LoadFooRequest
import event.MyEvent
import event.UpdateBarRequest
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.dom.a
import react.dom.h1
import react.dom.input
import react.dom.p
import state.Bar

fun RBuilder.bar(sendEvent:(MyEvent)->Unit, bar: Bar) {
    h1 {
        +"Bar"
    }
    input {
        attrs {
            value = bar.barString
            onChangeFunction = { event ->
                val target = event.target as HTMLInputElement
                sendEvent(UpdateBarRequest(target.value))
            }
        }
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
