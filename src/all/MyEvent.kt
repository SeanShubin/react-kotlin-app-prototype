package all

interface MyEvent

object LoadFooRequest : MyEvent {
    override fun toString(): String = "NavFooRequest"
}

object LoadBarRequest : MyEvent {
    override fun toString(): String = "NavBarRequest"
}

data class UpdateFooRequest(val newValue: String) : MyEvent
data class UpdateBarRequest(val newValue: String) : MyEvent
data class StoreFooRequest(val newValue: String) : MyEvent
data class StoreBarRequest(val newValue: String) : MyEvent
data class LoadFooResponse(val newValue: String) : MyEvent
data class LoadBarResponse(val newValue: String) : MyEvent

interface EventLoop {
    fun reactTo(state: MyState, event: MyEvent): StateAndEffects
}

data class StateAndEffects(val state: MyState, val effects: List<MyEffect>)
interface MyEffect {
    fun apply(handleEvent: (MyEvent) -> Unit, environment: Environment)
}
interface Environment

class EventLoopImpl:EventLoop{
    override fun reactTo(state: MyState, event: MyEvent): StateAndEffects {
        console.log("oldState", state)
        console.log("event", event)
        val newState = when(event){
            is LoadFooRequest -> Foo("nav foo string")
            is LoadBarRequest -> Bar("nav bar string")
            is UpdateFooRequest -> Foo(event.newValue)
            is UpdateBarRequest -> Bar(event.newValue)
            else -> Debug
        }
        val effects = emptyList<MyEffect>()
        console.log("newState", newState)
        console.log("effects", effects)
        return StateAndEffects(newState, effects)
    }

}

class EnvironmentImpl(private val api: Api) : Environment {

}
