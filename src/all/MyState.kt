package all

interface MyState
data class FooState(val fooString: String) : MyState
data class BarState(val barString: String) : MyState
data class UnsupportedEventState(val eventString: String) : MyState
