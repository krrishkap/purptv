package tv.purple.monolith.core.models.flag.variants

import tv.purple.monolith.core.models.flag.core.Variant

enum class ChatHistoryVariant(val value: String) : Variant {
    DISABLED("disabled"),
    TWITCH("twitch"),
    ROBOTTY("robotty"),
    ORANGE("orange");

    override fun getDefault(): Variant {
        return TWITCH;
    }

    override fun toString(): String {
        return value
    }
}