package tv.purple.monolith.component.badges

import io.reactivex.disposables.CompositeDisposable
import tv.purple.monolith.component.badges.model.factory.RoomFactory
import tv.purple.monolith.models.data.badges.Badge
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BadgeProvider @Inject constructor(
    private val roomFactory: RoomFactory
) {
    private var global = roomFactory.create()

    private val disposables = CompositeDisposable()

    fun clear() {
        disposables.clear()
        global = roomFactory.create()
    }

    fun getBadges(userId: Int): List<Badge> {
        return global.getBadges(userId = userId)
    }

    fun fetch() {
        global.fetch()
    }

    fun refreshBadges() {
        global.refresh()
    }

    fun hasBadges(userId: Int): Boolean {
        return global.hasBadges(userId)
    }

    fun rebuild() {
        clear()
        fetch()
    }
}