package tv.purple.monolith.core.models.flag.variants

import tv.purple.monolith.core.models.flag.core.Variant

enum class Timestamps(
    val value: String,
    val format: String
) : Variant {
    DEFAULT("default", ""),
    HOUR12("h12", "h:mm"),
    HOUR12S("h12s", "h:mm:ss"),
    HOUR24("h24", "H:mm"),
    HOUR24S("h24s", "H:mm:ss"),
    PADDED("padded", "hh:mm"),
    PADDEDS("paddeds", "hh:mm:ss"),
    PADDED24("padded24", "HH:mm"),
    PADDED24S("padded24s", "HH:mm:ss");

    override fun getDefault(): Variant {
        return DEFAULT
    }

    override fun toString(): String {
        return value
    }
}