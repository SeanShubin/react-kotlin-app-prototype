package all

import org.w3c.dom.HTMLElement
import react.RBuilder
import react.RReadableRef

fun RBuilder.dispatch(sendEvent: (MyEvent) -> Unit,
                      focusMe: RReadableRef<HTMLElement>,
                      page: MyState) {
    try {
        when (page) {
            is FooState -> foo(sendEvent, focusMe, page)
            is BarState -> bar(sendEvent, focusMe, page)
            is UnsupportedEventState -> unsupportedEvent(sendEvent, page)
        }
    } catch (exception: Exception) {
        debug(sendEvent, exception)
    }
}
