package dispatch

import bar.bar
import event.MyEvent
import foo.foo
import state.Bar
import state.Foo
import state.MyState
import react.RBuilder

fun RBuilder.dispatch(sendEvent:(MyEvent) -> Unit, page: MyState){
    when(page){
        is Foo -> foo(sendEvent, page)
        is Bar -> bar(sendEvent, page)
    }
}
