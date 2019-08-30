package api

import kotlin.js.Promise

interface Api {
    fun getFoo(): Promise<String?>
    fun setFoo(newFoo: String): Promise<Unit>
    fun getBar(): Promise<String?>
    fun setBar(newFoo: String): Promise<Unit>
}
