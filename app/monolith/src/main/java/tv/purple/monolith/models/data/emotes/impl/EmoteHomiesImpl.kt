package tv.purple.monolith.models.data.emotes.impl

import tv.purple.monolith.models.wrapper.EmotePackageSet
import tv.purple.monolith.models.data.emotes.Emote

data class EmoteHomiesImpl(
    private val emoteId: String,
    private val emoteCode: String,
    private val packageSet: EmotePackageSet
) : Emote {
    override fun getCode() = emoteCode
    override fun isAnimated(): Boolean = true
    override fun getPackageSet(): EmotePackageSet = packageSet
    override fun isZeroWidth(): Boolean = false

    override fun getUrl(size: Emote.Size): String = when (size) {
        Emote.Size.LARGE -> getEmoteUrl(emoteId, "3x")
        Emote.Size.MEDIUM -> getEmoteUrl(emoteId, "2x")
        Emote.Size.SMALL -> getEmoteUrl(emoteId, "1x")
    }

    companion object {
        private const val CDN_URL = "https://itzalex.github.io/emote/"

        private fun getEmoteUrl(emoteId: String, size: String): String {
            return "$CDN_URL$emoteId/$size"
        }
    }
}