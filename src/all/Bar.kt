package all

import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onFocusFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.a
import react.dom.h1
import react.dom.input
import react.dom.p

interface BarProps : RProps {
    var sendEvent: (MyEvent) -> Unit
    var barState: BarState
}

class Bar : RComponent<BarProps, RState>() {
    private val focusMe: RReadableRef<HTMLInputElement> = createRef()

    override fun componentDidMount() {
        focusMe.current?.focus()
    }

    override fun RBuilder.render() {
        h1 {
            +"Bar"
        }
        input {
            attrs {
                ref = focusMe
                value = props.barState.barString
                onChangeFunction = { event ->
                    val target = event.target as HTMLInputElement
                    props.sendEvent(UpdateBarRequest(target.value))
                }
                onBlurFunction = { event ->
                    val target = event.target as HTMLInputElement
                    props.sendEvent(StoreBarRequest(target.value))
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
                        props.sendEvent(LoadFooRequest)
                    }
                }
            }
        }
    }
}

fun RBuilder.bar(sendEvent: (MyEvent) -> Unit,
                 barState: BarState) = child(Bar::class) {
    attrs.sendEvent = sendEvent
    attrs.barState = barState
}
