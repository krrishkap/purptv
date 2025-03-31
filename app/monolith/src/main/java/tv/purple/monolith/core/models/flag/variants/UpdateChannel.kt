package tv.purple.monolith.core.models.flag.variants

import tv.purple.monolith.core.models.flag.core.Variant

enum class UpdateChannel(val value: String) : Variant {
    Disabled("disabled"),
    Release("release"),
    Dev("dev");

    override fun getDefault(): Variant {
        return Release
    }

    override fun toString(): String {
        return value
    }
}