package all

import org.w3c.dom.Storage
import kotlin.js.Promise

class StorageApi(private val storage: Storage) : Api {
    override fun getFoo(): Promise<String> {
        console.log("StorageApi.getFoo()")
        return Promise.resolve(storage.getItem("foo") ?: "null")
    }

    override fun setFoo(newFoo: String): Promise<Unit> {
        console.log("StorageApi.setFoo($newFoo)")
        storage.setItem("foo", newFoo)
        return Promise.resolve(Unit)
    }

    override fun getBar(): Promise<String> {
        console.log("StorageApi.getBar()")
        return Promise.resolve(storage.getItem("bar") ?: "null")
    }

    override fun setBar(newBar: String): Promise<Unit> {
        console.log("StorageApi.setBar($newBar)")
        storage.setItem("bar", newBar)
        return Promise.resolve(Unit)
    }
}
