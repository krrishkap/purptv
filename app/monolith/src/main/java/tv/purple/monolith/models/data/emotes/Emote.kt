package tv.purple.monolith.models.data.emotes

import tv.purple.monolith.models.wrapper.EmotePackageSet

interface Emote {
    fun getCode(): String

    fun getUrl(size: Size): String

    fun isAnimated(): Boolean

    fun getPackageSet(): EmotePackageSet

    fun isZeroWidth(): Boolean

    enum class Size {
        LARGE,
        MEDIUM,
        SMALL
    }
}