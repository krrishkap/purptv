package tv.purple.monolith.core.models.flag.core

class IntRangeValue(
    val minValue: Int,
    val maxValue: Int,
    private var currentValue: Int = minValue,
    val step: Int = 1,
    private val defaultValue: Int = currentValue
) : ValueHolder {
    override fun getValue(): Int = currentValue
    override fun getDefaultValue(): Int = defaultValue

    override fun setValue(value: Any?) {
        this.currentValue = IntValue.fromAny(value)
    }
}