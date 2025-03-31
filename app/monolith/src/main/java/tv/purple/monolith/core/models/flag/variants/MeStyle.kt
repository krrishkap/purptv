package tv.purple.monolith.core.models.flag.variants

import tv.purple.monolith.core.models.flag.core.Variant

enum class MeStyle(val value: String) : Variant {
    DISABLED("disabled"),
    COLORED("colored"),
    ITALIC("italic"),
    ITALIC_COLORED("italic_colored");

    override fun getDefault(): Variant {
        return COLORED;
    }

    override fun toString(): String {
        return value
    }
}