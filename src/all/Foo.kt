package all

import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onFocusFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*

interface FooProps : RProps {
    var sendEvent: (MyEvent) -> Unit
    var fooState: FooState
    var focusMe: RReadableRef<HTMLElement>
}

class Foo : RComponent<FooProps, RState>() {
    private fun setFocusToDefault() {
        props.focusMe.current?.focus()
    }

    override fun componentDidMount() {
        setFocusToDefault()
    }

    override fun RBuilder.render() {
        h1 {
            +"Class Style"
        }
        input {
            attrs {
                ref = props.focusMe
                value = props.fooState.fooString
                onChangeFunction = { event ->
                    val target = event.target as HTMLInputElement
                    props.sendEvent(UpdateFooRequest(target.value))
                }
                onBlurFunction = { event ->
                    val target = event.target as HTMLInputElement
                    props.sendEvent(StoreFooRequest(target.value))
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
                +"Function Style"
                attrs {
                    onClickFunction = {
                        props.sendEvent(LoadBarRequest)
                    }
                }
            }
        }
    }
}

fun RBuilder.foo(sendEvent: (MyEvent) -> Unit,
                 focusMe: RReadableRef<HTMLElement>,
                 fooState: FooState) = child(Foo::class) {
    attrs.sendEvent = sendEvent
    attrs.fooState = fooState
    attrs.focusMe = focusMe
}
