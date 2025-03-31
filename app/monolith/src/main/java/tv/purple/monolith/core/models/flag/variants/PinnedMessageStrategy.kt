package tv.purple.monolith.core.models.flag.variants

import tv.purple.monolith.core.models.flag.core.Variant

enum class PinnedMessageStrategy(val value: String) : Variant {
    Default("default"),
    Disabled("disabled"),
    SEC30("sec30");

    override fun getDefault(): Variant {
        return Default
    }

    override fun toString(): String {
        return value
    }
}