package event

import state.MyState
import state.Foo
import state.Bar
import state.Debug

interface MyEvent

object NavFooRequest : MyEvent {
    override fun toString(): String = "NavFooRequest"
}
object NavBarRequest : MyEvent{
    override fun toString(): String = "NavBarRequest"
}
data class FooStringChangeRequest(val newValue: String) : MyEvent
data class BarStringChangeRequest(val newValue: String) : MyEvent

interface EventLoop {
    fun reactTo(state: MyState, event: MyEvent): StateAndEffects
}

data class StateAndEffects(val state: MyState, val effects: List<Effect>)
interface Effect {
    fun apply(handleEvent: (MyEvent) -> Unit, environment: Environment)
}
interface Environment

class EventLoopImpl:EventLoop{
    override fun reactTo(state: MyState, event: MyEvent): StateAndEffects {
        console.log("oldState", state)
        console.log("event", event)
        val newState = when(event){
            is NavFooRequest -> Foo("nav foo string")
            is NavBarRequest -> Bar("nav bar string")
            is FooStringChangeRequest -> Foo(event.newValue)
            is BarStringChangeRequest -> Bar(event.newValue)
            else -> Debug
        }
        val effects = emptyList<Effect>()
        console.log("newState", newState)
        console.log("effects", effects)
        return StateAndEffects(newState, effects)
    }

}

class EnvironmentImpl:Environment {

}