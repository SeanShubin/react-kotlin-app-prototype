package app

import all.*
import react.*

interface AppState : RState {
    var myState: MyState
}

interface AppProps : RProps {
    var eventLoop: EventLoop
    var environment: Environment
    var test: String
}

class App : RComponent<AppProps, AppState>() {
    override fun componentDidMount() {
        handleEvent(LoadFooRequest)
    }

    override fun RBuilder.render() {
        dispatch(::handleEvent, state.myState)
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
    attrs.test = "blah"
}
