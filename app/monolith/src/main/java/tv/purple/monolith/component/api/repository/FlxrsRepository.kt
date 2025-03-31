package tv.purple.monolith.component.api.repository

import io.reactivex.Single
import tv.purple.monolith.component.api.data.source.FlxrsDataSource
import tv.purple.monolith.models.data.badges.BadgeSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlxrsRepository @Inject constructor(
    private val dataSource: FlxrsDataSource
) {
    fun getBadges(): Single<BadgeSet> {
        return dataSource.getBadges()
    }
}