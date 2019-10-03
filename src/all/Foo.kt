package all

import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onFocusFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.dom.a
import react.dom.h1
import react.dom.input
import react.dom.p

fun RBuilder.foo(sendEvent: (MyEvent) -> Unit, foo: FooState) {
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
            onBlurFunction = { event ->
                val target = event.target as HTMLInputElement
                sendEvent(StoreFooRequest(target.value))
            }
            onFocusFunction = { event ->
                val target = event.target as HTMLInputElement
                target.setSelectionRange(0, target.value.length)
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
