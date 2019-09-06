package all

interface MyEffect {
    fun apply(handleEvent: (MyEvent) -> Unit, environment: Environment)
}

object LoadFooEffect : MyEffect {
    override fun apply(handleEvent: (MyEvent) -> Unit, environment: Environment) {
        environment.api.getFoo().then { foo ->
            handleEvent(LoadFooResponse(foo))
        }
    }

    override fun toString(): String = "LoadFooEffect"
}

object LoadBarEffect : MyEffect {
    override fun apply(handleEvent: (MyEvent) -> Unit, environment: Environment) {
        environment.api.getBar().then { bar ->
            handleEvent(LoadBarResponse(bar))
        }
    }

    override fun toString(): String = "LoadBarEffect"
}

data class StoreFooEffect(val newValue: String) : MyEffect {
    override fun apply(handleEvent: (MyEvent) -> Unit, environment: Environment) {
        environment.api.setFoo(newValue).then {
            handleEvent(LoadFooResponse(newValue))
        }
    }

    override fun toString(): String = "LoadFooEffect"
}

data class StoreBarEffect(val newValue: String) : MyEffect {
    override fun apply(handleEvent: (MyEvent) -> Unit, environment: Environment) {
        environment.api.setBar(newValue).then {
            handleEvent(LoadBarResponse(newValue))
        }
    }

    override fun toString(): String = "LoadBarEffect"
}
