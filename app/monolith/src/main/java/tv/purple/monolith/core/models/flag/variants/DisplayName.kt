package tv.purple.monolith.core.models.flag.variants

import tv.purple.monolith.core.models.flag.core.Variant

enum class DisplayName(val value: String) : Variant {
    InternationalUsername("international_username"),
    International("international"),
    Username("username");

    override fun getDefault(): Variant {
        return InternationalUsername
    }

    override fun toString(): String {
        return value
    }
}