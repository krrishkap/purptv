package tv.purple.monolith.core.models.flag.core

import com.google.gson.Gson
import kotlin.reflect.KClass

class ObjectValue<T : Any>(
    obj: T,
    private val clz: KClass<T>
) : ValueHolder {
    private var value: T? = fromAny(obj)
    private val gson by lazy { Gson() }

    override fun getValue(): String {
        return gson.toJson(value)
    }

    override fun setValue(value: Any?) {
        this.value = fromAny(value)
    }

    override fun getDefaultValue(): Any? {
        return null
    }

    @Suppress("UNCHECKED_CAST")
    private fun fromAny(obj: Any?): T? {
        if (obj == null) {
            return null
        }

        return when {
            obj is String -> {
                if (obj.isBlank()) {
                    null
                } else {
                    try {
                        gson.fromJson(obj, clz.java)
                    } catch (e: Exception) {
                        null
                    }
                }
            }

            clz.java.isAssignableFrom(obj.javaClass) -> obj as T
            else -> throw IllegalStateException("Cannot convert ${obj.javaClass.name} to ${clz.java.name}")
        }
    }

    fun getObject(): T? {
        return value
    }
}