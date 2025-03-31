package tv.purple.monolith.component.api.data.source

import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.FlxrsApi
import tv.purple.monolith.component.api.data.mapper.FlxrsMapper
import tv.purple.monolith.models.data.badges.BadgeSet
import javax.inject.Inject

class FlxrsDataSource @Inject constructor(
    private val flxrsApi: FlxrsApi,
    private val flxrsMapper: FlxrsMapper
) {
    fun getBadges(): Single<BadgeSet> {
        return flxrsApi.badges().map(flxrsMapper::map)
    }
}