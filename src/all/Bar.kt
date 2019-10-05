package all

import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onFocusFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RReadableRef
import react.dom.*

fun RBuilder.bar(sendEvent: (MyEvent) -> Unit,
                 focusMe: RReadableRef<HTMLElement>,
                 bar: BarState) {
    fun setFocusToDefault() {
        focusMe.current?.focus()
    }
    h1 {
        +"Function Style"
    }
    input {
        attrs {
            ref = focusMe
            value = bar.barString
            autoFocus = true
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
        button {
            +"set focus"
            attrs {
                onClickFunction = { event ->
                    setFocusToDefault()
                }
            }
        }
    }
    p {
        a(href = "#") {
            +"Class Style"
            attrs {
                onClickFunction = {
                    sendEvent(LoadFooRequest)
                }
            }
        }
    }
}
