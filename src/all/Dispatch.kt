package all

import react.RBuilder

fun RBuilder.dispatch(sendEvent: (MyEvent) -> Unit,
                      page: MyState) {
    try {
        when (page) {
            is Foo -> foo(sendEvent, page)
            is Bar -> bar(sendEvent, page)
            is UnsupportedEvent -> unsupportedEvent(sendEvent, page)
        }
    } catch (exception: Exception) {
        debug(sendEvent, exception)
    }
}
