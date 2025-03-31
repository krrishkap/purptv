package tv.purple.monolith.component.api.data.mapper

import tv.purple.monolith.core.util.FileUtil.fromHexToUUID
import tv.purple.monolith.models.data.badges.impl.BadgeImpl
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.data.badges.impl.BadgeHomiesImpl
import tv.purple.monolith.models.data.emotes.EmoteSet
import tv.purple.monolith.models.data.emotes.impl.EmoteHomiesImpl
import tv.purple.monolith.models.retrofit.homies.BadgesResponse
import tv.purple.monolith.models.retrofit.homies.ChannelEmotes
import tv.purple.monolith.models.retrofit.homies.ChatterinoBadgesResponse
import tv.purple.monolith.models.retrofit.homies.Emote
import tv.purple.monolith.models.wrapper.EmotePackageSet
import javax.inject.Inject

class HomiesMapper @Inject constructor() {
    fun map(emotes: List<Emote>): EmoteSet {
        return EmoteSet(emotes.map { emote ->
            EmoteHomiesImpl(
                emoteId = emote.id,
                emoteCode = emote.name,
                packageSet = EmotePackageSet.HomiesGlobal
            )
        })
    }

    fun map(entry: Map<String, ChannelEmotes>, channelId: Int): EmoteSet {
        val emotes = entry[channelId.toString()]?.emotes ?: emptyList()

        return map(emotes)
    }

    fun addBadges(builder: BadgeSet.Builder, response: BadgesResponse): BadgeSet.Builder {
        return builder.apply {
            response.badges?.forEach { badge ->
                val url = badge.image2 ?: badge.image1 ?: badge.image3
                if (url != null) {
                    val impl = BadgeImpl(
                        code = "",
                        url = url,
                    )
                    badge.users?.forEach { rawUserId ->
                        rawUserId?.toIntOrNull()?.let { userId ->
                            addBadge(impl, userId)
                        }
                    }
                }
            }
        }
    }

    fun addBadges(builder: BadgeSet.Builder, response: ChatterinoBadgesResponse): BadgeSet.Builder {
        return builder.apply {
            response.badges?.forEach { badge ->
                badge?.badgeId?.let { badgeId ->
                    badge.userId?.toIntOrNull()?.let { userId ->
                        addBadge(
                            BadgeHomiesImpl(
                                badgeId = badgeId.fromHexToUUID()
                            ), userId
                        )
                    }
                }
            }
        }
    }
}