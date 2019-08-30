package app

import dispatch.dispatch
import event.*
import react.*
import state.Foo
import state.MyState

interface AppState : RState {
    var myState: MyState
}

interface AppProps : RProps {
    var eventLoop: EventLoop
    var environment: Environment
}

class App : RComponent<AppProps, AppState>() {
    override fun AppState.init() {
        myState = Foo("initial foo string")
    }

    override fun RBuilder.render() {
        fun handleEvent(event: MyEvent){
            val (newState, effects) = props.eventLoop.reactTo(state.myState, event)
            setState {
                myState = newState
                effects.forEach { it.apply(::handleEvent, props.environment) }
            }
        }
        dispatch(::handleEvent, state.myState)
    }
}

fun RBuilder.app(eventLoop: EventLoop,
                 environment: Environment) = child(App::class) {
    attrs.eventLoop = eventLoop
    attrs.environment = environment
}
