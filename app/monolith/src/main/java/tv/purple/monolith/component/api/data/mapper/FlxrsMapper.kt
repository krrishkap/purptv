package tv.purple.monolith.component.api.data.mapper

import tv.purple.monolith.models.data.badges.impl.BadgeImpl
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.retrofit.flxrs.DankChatBadgeResponse
import javax.inject.Inject

class FlxrsMapper @Inject constructor() {
    fun map(badges: List<DankChatBadgeResponse>): BadgeSet {
        val builder = BadgeSet.Builder()

        badges.forEach { badge ->
            val badgeImpl = BadgeImpl(
                code = "flxrs",
                url = badge.url
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

        return builder.build()
    }
}