package tv.purple.monolith.component.api.repository

import io.reactivex.Single
import tv.purple.monolith.component.api.data.source.ChatterinoApiDataSource
import tv.purple.monolith.models.data.badges.BadgeSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatterinoRepository @Inject constructor(
    private val chatterinoApiDataSource: ChatterinoApiDataSource
) {
    fun getChatterinoBadges(): Single<BadgeSet> {
        return chatterinoApiDataSource.getBadges()
    }
}