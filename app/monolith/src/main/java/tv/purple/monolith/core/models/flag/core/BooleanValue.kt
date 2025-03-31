package tv.purple.monolith.core.models.flag.core

class BooleanValue(
    bool: Any? = false,
    private val defValue: Boolean = fromAny(bool)
) : ValueHolder {
    private var value = fromAny(bool)

    override fun getDefaultValue(): Boolean = defValue
    override fun getValue(): Boolean = value

    override fun setValue(value: Any?) {
        this.value = fromAny(value)
    }

    companion object {
        private fun fromAny(obj: Any?): Boolean {
            return when (obj) {
                is Boolean -> obj
                is String -> obj.equals("true", ignoreCase = true)
                is Number -> obj.toInt() != 0
                null -> false
                else -> try {
                    obj.toString().equals("true", ignoreCase = true)
                } catch (e: Exception) {
                    false
                }
            }
        }
    }
}