package tv.purple.monolith.models.data.emotes.impl

import tv.purple.monolith.models.data.emotes.Emote
import tv.purple.monolith.models.wrapper.EmotePackageSet

data class EmoteImpl(
    private val emoteCode: String,
    private val smallUrl: String,
    private val mediumUrl: String?,
    private val largeUrl: String?,
    private val animated: Boolean,
    private val packageSet: EmotePackageSet,
    private val isZeroWidth: Boolean = false
) : Emote {
    override fun getCode() = emoteCode
    override fun isAnimated(): Boolean = animated
    override fun getPackageSet(): EmotePackageSet = packageSet
    override fun isZeroWidth(): Boolean = isZeroWidth

    override fun getUrl(size: Emote.Size): String {
        return when (size) {
            Emote.Size.LARGE -> largeUrl ?: mediumUrl ?: smallUrl
            Emote.Size.MEDIUM -> mediumUrl ?: smallUrl
            Emote.Size.SMALL -> smallUrl
        }
    }
}