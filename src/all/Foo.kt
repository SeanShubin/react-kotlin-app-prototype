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

interface FooProps : RProps {
    var sendEvent: (MyEvent) -> Unit
    var fooState: FooState
}

class Foo : RComponent<FooProps, RState>() {
    private val focusMe: RReadableRef<HTMLInputElement> = createRef()

    override fun componentDidMount() {
        focusMe.current?.focus()
    }

    override fun RBuilder.render() {
        h1 {
            +"Foo"
        }
        input {
            attrs {
                ref = focusMe
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
            a(href = "#") {
                +"Bar"
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
                 fooState: FooState) = child(Foo::class) {
    attrs.sendEvent = sendEvent
    attrs.fooState = fooState
}
