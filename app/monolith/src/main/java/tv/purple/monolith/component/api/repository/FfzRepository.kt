package tv.purple.monolith.component.api.repository

import io.reactivex.Single
import tv.purple.monolith.component.api.data.source.FfzAPRemoteDataSource
import tv.purple.monolith.component.api.data.source.FfzRemoteDataSource
import tv.purple.monolith.models.data.badges.BadgeSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfzRepository @Inject constructor(
    private val ffzDataSource: FfzRemoteDataSource,
    private val ffzAPRemoteDataSource: FfzAPRemoteDataSource
) {
    fun getFfzBadges(): Single<BadgeSet> {
        return ffzDataSource.getBadges()
    }

    fun getFfzAPBadges(): Single<BadgeSet> {
        return ffzAPRemoteDataSource.getBadges()
    }
}