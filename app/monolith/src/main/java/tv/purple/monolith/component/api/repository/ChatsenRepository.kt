package tv.purple.monolith.component.api.repository

import io.reactivex.Single
import tv.purple.monolith.component.api.data.source.ChatsenDataSource
import tv.purple.monolith.models.data.badges.BadgeSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatsenRepository @Inject constructor(
    private val dataSource: ChatsenDataSource
) {
    fun getBadges(): Single<BadgeSet> {
        return dataSource.getBadges()
    }
}