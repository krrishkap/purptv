package tv.purple.monolith.core.models.flag.core

import tv.purple.monolith.core.models.flag.core.Variant.Companion.fromString
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class ListValue<T : Variant>(
    variant: KClass<T>,
    private val defValue: Variant = get(variant.java)
) : ValueHolder {
    private var currentVariant: Variant = get(variant.java)

    companion object {
        private fun <T : Variant> get(variant: Class<T>): Variant {
            return if (variant.isEnum) {
                val tObj = variant.enumConstants?.get(0) as Variant?
                tObj?.getDefault() ?: throw IllegalStateException("<ListValue>")
            } else {
                variant.newInstance().getDefault()
            }
        }
    }

    fun getVariant(): Variant {
        return currentVariant
    }

    override fun getValue(): String {
        return currentVariant.toString()
    }

    override fun setValue(value: Any?) {
        currentVariant = if (value is Variant) {
            value
        } else {
            currentVariant.fromString(
                StringValue.fromAny(value)
            ) ?: currentVariant.getDefault()
        }
    }

    override fun getDefaultValue(): Any {
        return defValue
    }
}