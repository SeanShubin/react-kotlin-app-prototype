package all

import react.RBuilder

fun RBuilder.dispatch(sendEvent:(MyEvent) -> Unit, page: MyState){
    when(page){
        is Foo -> foo(sendEvent, page)
        is Bar -> bar(sendEvent, page)
    }
}
