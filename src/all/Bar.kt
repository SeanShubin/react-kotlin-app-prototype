package all

import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onFocusFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RReadableRef
import react.createRef
import react.dom.a
import react.dom.h1
import react.dom.input
import react.dom.p

fun RBuilder.bar(sendEvent: (MyEvent) -> Unit, bar: BarState) {
    val focusMe: RReadableRef<HTMLInputElement> = createRef()
    h1 {
        +"Bar"
    }
    input {
        attrs {
            ref = focusMe
            value = bar.barString
            onChangeFunction = { event ->
                val target = event.target as HTMLInputElement
                sendEvent(UpdateBarRequest(target.value))
            }
            onBlurFunction = { event ->
                val target = event.target as HTMLInputElement
                sendEvent(StoreBarRequest(target.value))
            }
            onFocusFunction = { event ->
                val target = event.target as HTMLInputElement
                target.setSelectionRange(0, target.value.length)
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
