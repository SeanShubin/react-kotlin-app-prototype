package all

interface MyState
data class Foo(val fooString: String) : MyState
data class Bar(val barString: String) : MyState
data class UnsupportedEvent(val eventString: String) : MyState
