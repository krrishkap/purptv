package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.ChatterinoApi
import tv.purple.monolith.component.api.data.mapper.ChatterinoApiMapper
import tv.purple.monolith.models.data.badges.BadgeSet
import javax.inject.Inject

class ChatterinoApiDataSource @Inject constructor(
    private val api: ChatterinoApi,
    private val mapper: ChatterinoApiMapper,
    private val scheduler: Scheduler
) {
    fun getBadges(): Single<BadgeSet> {
        return api.getBadges()
            .subscribeOn(scheduler)
            .map(mapper::map)
    }
}