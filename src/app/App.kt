package app

import all.Environment
import all.EventLoop
import all.MyEvent
import all.dispatch
import react.*
import state.Foo
import state.MyState

interface AppState : RState {
    var myState: MyState
}

interface AppProps : RProps {
    var eventLoop: EventLoop
    var environment: Environment
    var test: String
}

class App : RComponent<AppProps, AppState>() {
    override fun AppState.init() {
//        console.log("attrs.test", props.test)

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
    attrs.test = "blah"
}
