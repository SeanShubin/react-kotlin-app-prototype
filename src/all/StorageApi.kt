package api

import org.w3c.dom.Storage
import kotlin.js.Promise

class StorageApi(private val storage: Storage) : Api {
    override fun getFoo(): Promise<String?> {
        return Promise.resolve(storage.getItem("foo"))
    }

    override fun setFoo(newFoo: String): Promise<Unit> {
        storage.setItem("foo", newFoo)
        return Promise.resolve(Unit)
    }

    override fun getBar(): Promise<String?> {
        return Promise.resolve(storage.getItem("bar"))
    }

    override fun setBar(newBar: String): Promise<Unit> {
        storage.setItem("bar", newBar)
        return Promise.resolve(Unit)
    }
}
