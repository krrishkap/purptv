package tv.purple.monolith.component.badges.model.room

import tv.purple.monolith.component.badges.model.BadgePackage
import tv.purple.monolith.models.data.badges.Badge

class RoomImpl : Room {
    private val packages: MutableList<BadgePackage> = mutableListOf()

    override fun add(pack: BadgePackage) {
        packages.add(pack)
    }

    override fun getBadges(userId: Int): List<Badge> {
        return packages.flatMap { pack ->
            pack.getBadges(userId = userId)
        }
    }

    override fun fetch() {
        packages.forEach { pack ->
            pack.refresh(force = true)
        }
    }

    override fun refresh() {
        packages.forEach { pack ->
            pack.refresh(force = false)
        }
    }

    override fun clear() {
        packages.forEach { pack ->
            pack.clear()
        }
        packages.clear()
    }

    override fun hasBadges(userId: Int): Boolean {
        return packages.any { pack ->
            pack.hasBadges(userId = userId)
        }
    }
}