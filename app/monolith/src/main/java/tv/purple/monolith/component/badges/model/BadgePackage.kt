package tv.purple.monolith.component.badges.model

import tv.purple.monolith.models.data.badges.Badge

interface BadgePackage {
    fun getBadges(userId: Int): Collection<Badge>

    fun refresh(force: Boolean)

    fun isEmpty(): Boolean

    fun clear()

    fun hasBadges(userId: Int): Boolean
}