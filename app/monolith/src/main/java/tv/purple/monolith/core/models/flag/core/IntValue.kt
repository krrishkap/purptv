package tv.purple.monolith.core.models.flag.core

class IntValue(
    i: Any? = 0,
    private val defValue: Int = fromAny(i)
) : ValueHolder {
    private var value = fromAny(i)

    override fun getValue(): Int = value
    override fun getDefaultValue(): Int = defValue

    override fun setValue(value: Any?) {
        this.value = fromAny(value)
    }

    companion object {
        fun fromAny(obj: Any?): Int {
            return when (obj) {
                is Int -> obj
                is Number -> obj.toInt()
                is String -> try {
                    obj.toInt()
                } catch (e: NumberFormatException) {
                    try {
                        obj.toDouble().toInt()
                    } catch (e: NumberFormatException) {
                        0
                    }
                }

                null -> 0
                else -> try {
                    obj.toString().toInt()
                } catch (e: NumberFormatException) {
                    0
                }
            }
        }
    }
}