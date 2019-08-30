package state

interface MyState
data class Foo(val fooString: String) : MyState
data class Bar(val barString: String) : MyState
object Debug : MyState {
    override fun toString(): String = "Debug"
}