package app

import all.*
import org.w3c.dom.HTMLElement
import react.*

interface AppState : RState {
    var myState: MyState
}

interface AppProps : RProps {
    var eventLoop: EventLoop
    var environment: Environment
}

class App : RComponent<AppProps, AppState>() {
    override fun componentDidMount() {
        handleEvent(LoadFooRequest)
    }

    override fun RBuilder.render() {
        val focusMe: RReadableRef<HTMLElement> = createRef()
        dispatch(::handleEvent, focusMe, state.myState)
    }

    private fun handleEvent(event: MyEvent) {
        val (newState, effects) = props.eventLoop.reactTo(state.myState, event)
        setState {
            myState = newState
            effects.forEach { it.apply(::handleEvent, props.environment) }
        }
    }
}

fun RBuilder.app(eventLoop: EventLoop,
                 environment: Environment) = child(App::class) {
    attrs.eventLoop = eventLoop
    attrs.environment = environment
}
