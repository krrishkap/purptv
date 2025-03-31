package tv.purple.monolith.component.api.data.mapper

import tv.purple.monolith.models.data.badges.impl.BadgeImpl
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.retrofit.chatsen.Badges2Response
import tv.purple.monolith.models.retrofit.chatsen.BadgesResponse
import javax.inject.Inject

class ChatsenMapper @Inject constructor() {
    fun addBadges(builder: BadgeSet.Builder, data: BadgesResponse) {
        builder.apply {
            val badges = data.badges?.associateBy({ it.name }, {
                it.image?.let { image ->
                    BadgeImpl(
                        code = "chatsen",
                        url = image
                    )
                }
            }) ?: emptyMap()

            data.users?.forEach { user ->
                user.badges?.mapNotNull { badges[it.badgeName] }?.forEach { badge ->
                    user.id?.toIntOrNull()?.let { userId ->
                        addBadge(
                            badge = badge,
                            userId = userId
                        )
                    }
                }
            }
        }
    }

    fun addBadges(builder: BadgeSet.Builder, data: List<Badges2Response>) {
        builder.apply {
            data.forEach { b2r ->
                b2r.mipmap.firstOrNull()?.let { url ->
                    val badge = BadgeImpl(
                        code = "chatsen",
                        url = url
                    )

                    b2r.users.forEach { user ->
                        user.toIntOrNull()?.let { userId ->
                            addBadge(
                                badge = badge,
                                userId = userId
                            )
                        }
                    }
                }
            }
        }
    }
}