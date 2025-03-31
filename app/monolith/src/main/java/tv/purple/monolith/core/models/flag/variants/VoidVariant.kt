package tv.purple.monolith.core.models.flag.variants

import tv.purple.monolith.core.models.flag.core.Variant

class VoidVariant : Variant {
    override fun getDefault(): Variant {
        return this
    }

    override fun toString(): String {
        return this.toString()
    }
}