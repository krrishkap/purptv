package tv.purple.monolith.core.models.flag.core

interface ValueHolder {
    fun getValue(): Any?
    fun setValue(value: Any?)
    fun getDefaultValue(): Any?
}