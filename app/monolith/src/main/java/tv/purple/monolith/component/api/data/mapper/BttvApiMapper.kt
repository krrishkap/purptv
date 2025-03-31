package tv.purple.monolith.component.api.data.mapper

import tv.purple.monolith.models.wrapper.EmotePackageSet
import tv.purple.monolith.models.data.badges.impl.BadgeImpl
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.data.emotes.Emote
import tv.purple.monolith.models.data.emotes.impl.EmoteImpl
import tv.purple.monolith.models.data.emotes.impl.EmoteBttvImpl
import tv.purple.monolith.models.retrofit.bttv.BttvBadge
import tv.purple.monolith.models.retrofit.bttv.BttvChannelData
import tv.purple.monolith.models.retrofit.bttv.BttvEmote
import tv.purple.monolith.models.retrofit.bttv.FfzEmote
import tv.purple.monolith.models.retrofit.bttv.FfzImageType
import javax.inject.Inject

class BttvApiMapper @Inject constructor() {
    fun mapChannelBttvEmotes(response: BttvChannelData, useWebp: Boolean): List<Emote> {
        return mapChannelBttvEmotes(
            emotes = response.sharedEmotes, useWebp
        ) + mapChannelBttvEmotes(
            emotes = response.channelEmotes, useWebp
        )
    }

    fun mapGlobalBttvEmotes(emotes: List<BttvEmote>, useWebp: Boolean): List<Emote> {
        return mapEmotes(emotes = emotes, isGlobalEmotes = true, useWebp)
    }

    fun mapChannelBttvEmotes(emotes: List<BttvEmote>, useWebp: Boolean): List<Emote> {
        return mapEmotes(emotes = emotes, isGlobalEmotes = false, useWebp)
    }

    private fun mapEmotes(
        emotes: List<BttvEmote>,
        isGlobalEmotes: Boolean,
        useWebp: Boolean
    ): List<Emote> {
        return emotes.map { emote ->
            EmoteBttvImpl(
                emoteId = emote.id,
                emoteCode = emote.code,
                animated = emote.imageType == FfzImageType.GIF,
                packageSet = if (!isGlobalEmotes) {
                    EmotePackageSet.BttvChannel
                } else {
                    EmotePackageSet.BttvGlobal
                },
                isZeroWidthEmote = if (isGlobalEmotes) {
                    BTTV_GLOBAL_ZW_CODES.contains(emote.code)
                } else {
                    false
                },
                useWebp = useWebp
            )
        }
    }

    fun mapGlobalFfzEmotes(emotes: List<FfzEmote>): List<Emote> {
        return mapFfzEmotes(emotes = emotes, isGlobalEmotes = true)
    }

    fun mapChannelFfzEmotes(emotes: List<FfzEmote>): List<Emote> {
        return mapFfzEmotes(emotes = emotes, isGlobalEmotes = false)
    }

    private fun mapFfzEmotes(emotes: List<FfzEmote>, isGlobalEmotes: Boolean): List<Emote> {
        return emotes.mapNotNull { emote ->
            val small = emote.images["1x"] ?: return@mapNotNull null

            EmoteImpl(
                emoteCode = emote.code,
                animated = false,
                smallUrl = small,
                mediumUrl = emote.images["2x"],
                largeUrl = emote.images["4x"],
                packageSet = if (!isGlobalEmotes) {
                    EmotePackageSet.FfzChannel
                } else {
                    EmotePackageSet.FfzGlobal
                }
            )
        }
    }

    fun map(response: List<BttvBadge>): BadgeSet {
        return BadgeSet.Builder().apply {
            response.forEach { badge ->
                val badgeImpl = BadgeImpl(
                    url = badge.badge.svg,
                    code = badge.badge.description
                )
                badge.providerId.toIntOrNull()?.let { userId ->
                    addBadge(
                        badge = badgeImpl,
                        userId = userId
                    )
                }
            }
        }.build()
    }


    companion object {
        private val BTTV_GLOBAL_ZW_CODES = hashSetOf(
            "SoSnowy", "IceCold", "SantaHat", "TopHat",
            "ReinDeer", "CandyCane", "cvMask", "cvHazmat"
        )
    }
}