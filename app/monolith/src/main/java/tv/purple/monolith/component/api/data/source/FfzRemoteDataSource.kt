package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.FfzApi
import tv.purple.monolith.component.api.data.mapper.FfzApiMapper
import tv.purple.monolith.models.data.badges.BadgeSet
import javax.inject.Inject

class FfzRemoteDataSource @Inject constructor(
    private val api: FfzApi,
    private val mapper: FfzApiMapper,
    private val scheduler: Scheduler
) {
    fun getBadges(): Single<BadgeSet> {
        return api.globalBadges()
            .subscribeOn(scheduler)
            .map(mapper::mapBadges)
    }
}