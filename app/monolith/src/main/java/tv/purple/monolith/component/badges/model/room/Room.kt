package tv.purple.monolith.component.badges.model.room

import tv.purple.monolith.component.badges.model.BadgePackage
import tv.purple.monolith.models.data.badges.Badge

interface Room {
    fun add(pack: BadgePackage)

    fun getBadges(userId: Int): List<Badge>

    fun fetch()

    fun refresh()

    fun clear()

    fun hasBadges(userId: Int): Boolean
}