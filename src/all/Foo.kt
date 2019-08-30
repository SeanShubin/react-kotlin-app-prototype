package foo

import event.LoadBarRequest
import event.MyEvent
import event.UpdateFooRequest
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.dom.a
import react.dom.h1
import react.dom.input
import react.dom.p
import state.Foo

fun RBuilder.foo(sendEvent:(MyEvent)->Unit, foo: Foo) {
    h1 {
        +"Foo"
    }
    input {
        attrs {
            value = foo.fooString
            onChangeFunction = { event ->
                val target = event.target as HTMLInputElement
                sendEvent(UpdateFooRequest(target.value))
            }
        }
    }
    p {
        a(href = "#") {
            +"Bar"
            attrs {
                onClickFunction = {
                    sendEvent(LoadBarRequest)
                }
            }
        }
    }
}
