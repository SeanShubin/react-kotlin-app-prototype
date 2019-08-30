package all

import react.RBuilder
import state.Bar
import state.Foo
import state.MyState

fun RBuilder.dispatch(sendEvent:(MyEvent) -> Unit, page: MyState){
    when(page){
        is Foo -> foo(sendEvent, page)
        is Bar -> bar(sendEvent, page)
    }
}
