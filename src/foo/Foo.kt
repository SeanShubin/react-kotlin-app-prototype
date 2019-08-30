package foo

import event.FooStringChangeRequest
import event.MyEvent
import event.NavBarRequest
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import state.Foo
import react.RBuilder
import react.dom.a
import react.dom.h1
import react.dom.input
import react.dom.p

fun RBuilder.foo(sendEvent:(MyEvent)->Unit, foo: Foo) {
    h1 {
        +"Foo"
    }
    input {
        attrs {
            value = foo.fooString
            onChangeFunction = { event ->
                val target = event.target as HTMLInputElement
                sendEvent(FooStringChangeRequest(target.value))
            }
        }
    }
    p {
        a(href = "#") {
            +"Bar"
            attrs {
                onClickFunction = {
                    sendEvent(NavBarRequest)
                }
            }
        }
    }
}
