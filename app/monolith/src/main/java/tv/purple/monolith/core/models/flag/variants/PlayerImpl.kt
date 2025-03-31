package tv.purple.monolith.core.models.flag.variants

import tv.purple.monolith.core.models.flag.core.Variant

enum class PlayerImpl(val value: String) : Variant {
    Default("default"),
    Core("core"),
    Exo("exo");

    override fun getDefault(): Variant {
        return Default
    }

    override fun toString(): String {
        return value
    }
}