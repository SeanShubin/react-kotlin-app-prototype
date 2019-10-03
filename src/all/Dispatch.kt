package all

import react.RBuilder

fun RBuilder.dispatch(sendEvent: (MyEvent) -> Unit,
                      page: MyState) {
    try {
        when (page) {
            is FooState -> foo(sendEvent, page)
            is BarState -> bar(sendEvent, page)
            is UnsupportedEventState -> unsupportedEvent(sendEvent, page)
        }
    } catch (exception: Exception) {
        debug(sendEvent, exception)
    }
}
