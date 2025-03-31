package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.FFZAPApi
import tv.purple.monolith.component.api.data.mapper.FfzApiMapper
import tv.purple.monolith.models.data.badges.BadgeSet
import javax.inject.Inject

class FfzAPRemoteDataSource @Inject constructor(
    private val api: FFZAPApi,
    private val mapper: FfzApiMapper,
    private val scheduler: Scheduler
) {
    fun getBadges(): Single<BadgeSet> {
        return api.supporters()
            .subscribeOn(scheduler)
            .map(mapper::mapBadges)
    }
}