package tv.purple.monolith.component.api.data.mapper

import tv.purple.monolith.models.wrapper.EmotePackageSet
import tv.purple.monolith.models.data.badges.impl.BadgeImpl
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.data.emotes.Emote
import tv.purple.monolith.models.data.emotes.impl.EmoteStvV3Impl
import tv.purple.monolith.models.retrofit.stv.BadgesData
import tv.purple.monolith.models.retrofit.stv.v3.EmoteLifecycle
import tv.purple.monolith.models.retrofit.stv.v3.EmoteSet
import tv.purple.monolith.models.retrofit.stv.v3.ImageFormatModel
import tv.purple.monolith.models.retrofit.stv.v3.ImageHost
import tv.purple.monolith.core.LoggerImpl
import java.math.BigInteger
import javax.inject.Inject

class StvApiMapper @Inject constructor() {
    fun mapGlobalEmotes(set: EmoteSet): List<Emote> {
        return mapEmotes(set = set, isChannelEmotes = false)
    }

    fun mapChannelEmotes(set: EmoteSet): List<Emote> {
        return mapEmotes(set = set, isChannelEmotes = true)
    }

    private fun mapEmotes(set: EmoteSet, isChannelEmotes: Boolean): List<Emote> {
        return set.emotes.mapNotNull { emote ->
            emote.data?.let { emotePartial ->
                if (emotePartial.lifecycle != EmoteLifecycle.Live) {
                    LoggerImpl.debug("Skip emote (lifecycle): $emotePartial")
                    return@mapNotNull null
                }
                if (!emotePartial.flags.isAllowedOnTwitch()) {
                    LoggerImpl.debug("Skip emote (disallowed): $emotePartial")
                    return@mapNotNull null
                }

                emotePartial.host.toSize()?.let { sizes ->
                    EmoteStvV3Impl(
                        emoteId = emote.id,
                        emoteCode = emote.name,
                        animated = emotePartial.animated,
                        packageSet = if (isChannelEmotes) {
                            EmotePackageSet.StvChannel
                        } else {
                            EmotePackageSet.StvGlobal
                        },
                        isZeroWidth = emotePartial.flags.isZeroWidthEmote(),
                        baseUrl = emotePartial.host.formatUrl(),
                        sizes = sizes
                    )
                }
            }
        }
    }

    fun mapBadges(response: BadgesData): BadgeSet {
        val builder = BadgeSet.Builder()

        response.badges.forEach { badge ->
            getBadgeUrl(badge.urls)?.let { url ->
                val badgeImpl = BadgeImpl(
                    code = "7tv",
                    url = url
                )
                badge.users.forEach { userIdString ->
                    userIdString.toIntOrNull()?.let { userId ->
                        builder.addBadge(
                            badge = badgeImpl,
                            userId = userId
                        )
                    }
                }
            }
        }

        return builder.build()
    }

    companion object {
        private fun getBadgeUrl(urls: List<List<String>>): String? {
            if (urls.isEmpty()) {
                return null
            }

            return when (urls.size) {
                0 -> return null
                1 -> urls[0][1]
                else -> urls[1][1]
            }
        }

        private fun Int.isZeroWidthEmote(): Boolean {
            return BigInteger.valueOf(toLong()).testBit(8)
        }

        private fun Int.isAllowedOnTwitch(): Boolean {
            return !BigInteger.valueOf(toLong()).testBit(24)
        }

        private fun pickBestFormat(files: List<ImageFormatModel>): List<ImageFormatModel> {
            val entries = files.groupBy { it.format }
            entries[ImageFormatModel.ImageFormat.WEBP]?.let {
                return it
            }
            entries[ImageFormatModel.ImageFormat.GIF]?.let {
                return it
            }
            entries[ImageFormatModel.ImageFormat.PNG]?.let {
                return it
            }

            return emptyList()
        }

        private fun ImageHost.formatUrl(): String {
            return url.let { url ->
                if (url.startsWith("//")) {
                    "https:$url"
                } else {
                    url
                }
            }.let { url ->
                if (url.endsWith("/")) {
                    url
                } else {
                    "$url/"
                }
            }
        }

        private fun ImageHost.toSize(): Triple<String, String?, String?>? {
            val pickedFiles = pickBestFormat(files).sortedBy { it.width }

            var small: String? = null
            var medium: String? = null
            var large: String? = null

            var width = 0
            pickedFiles.forEach { file ->
                if (width == 0) {
                    width = file.width
                    small = file.name
                } else {
                    when (file.width / width) {
                        1 -> small = file.name
                        2 -> medium = file.name
                        3 -> large = file.name
                        4 -> large = file.name
                    }
                }
            }

            small?.let { smallSize ->
                return Triple(smallSize, medium, large)
            }

            return null
        }
    }
}