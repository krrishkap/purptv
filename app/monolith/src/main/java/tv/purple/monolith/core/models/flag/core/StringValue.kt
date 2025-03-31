package tv.purple.monolith.core.models.flag.core

class StringValue(
    s: Any? = null,
    private val defValue: String = fromAny(s)
) : ValueHolder {
    private var value: String = fromAny(s)

    override fun getValue(): String = value
    override fun getDefaultValue(): String = defValue

    override fun setValue(value: Any?) {
        this.value = fromAny(value)
    }

    companion object {
        fun fromAny(obj: Any?): String {
            return when (obj) {
                is String -> obj
                null -> ""
                else -> obj.toString()
            }
        }
    }

}