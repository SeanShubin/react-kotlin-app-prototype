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

data class StateAndEffects(val state: MyState, val effects: List<MyEffect>) {
    fun addEffect(effect: MyEffect): StateAndEffects = copy(effects = effects + effect)
}

interface Environment {
    val api: Api
}


class EventLoopImpl : EventLoop {
    override fun reactTo(state: MyState, event: MyEvent): StateAndEffects {
        console.log("oldState", state)
        console.log("event", event)
        val default = StateAndEffects(state, emptyList())
        val result: StateAndEffects = when (event) {
            is StoreFooRequest -> default.addEffect(StoreFooEffect(event.newValue))
            is StoreBarRequest -> default.addEffect(StoreBarEffect(event.newValue))
            is LoadFooRequest -> default.addEffect(LoadFooEffect)
            is LoadBarRequest -> default.addEffect(LoadBarEffect)
            is UpdateFooRequest -> default.copy(state = Foo(event.newValue))
            is UpdateBarRequest -> default.copy(state = Bar(event.newValue))
            is LoadFooResponse -> default.copy(state = Foo(event.newValue))
            is LoadBarResponse -> default.copy(state = Bar(event.newValue))
            else -> default.copy(state = UnsupportedEvent(event.toString()))
        }
        console.log("newState", result.state)
        console.log("effects", result.effects)
        return result
    }

}

class EnvironmentImpl(override val api: Api) : Environment
