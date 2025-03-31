package tv.purple.monolith.component.api.data.mapper

import tv.purple.monolith.models.data.badges.impl.BadgeImpl
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.retrofit.chatterino.BadgesData
import javax.inject.Inject

class ChatterinoApiMapper @Inject constructor() {
    fun map(response: BadgesData): BadgeSet {
        return BadgeSet.Builder().apply {
            response.badges.forEach { badge ->
                val badgeImpl = BadgeImpl(
                    code = "chatterino",
                    url = badge.image2
                )
                badge.users.forEach { userIdString ->
                    userIdString.toIntOrNull()?.let { userId ->
                        addBadge(
                            badge = badgeImpl,
                            userId = userId
                        )
                    }
                }
            }
        }.build()
    }
}