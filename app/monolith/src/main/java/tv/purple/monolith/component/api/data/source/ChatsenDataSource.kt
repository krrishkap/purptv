package tv.purple.monolith.component.api.data.source

import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.Chatsen2Api
import tv.purple.monolith.component.api.data.api.ChatsenApi
import tv.purple.monolith.component.api.data.mapper.ChatsenMapper
import tv.purple.monolith.models.data.badges.BadgeSet
import javax.inject.Inject

class ChatsenDataSource @Inject constructor(
    private val api: ChatsenApi,
    private val api2: Chatsen2Api,
    private val mapper: ChatsenMapper
) {
    fun getBadges(): Single<BadgeSet> {
        return Single.zip(api.getBadges(), api2.getBadges()) { res1, res2 ->
            val builder = BadgeSet.Builder()
            mapper.addBadges(builder, res1)
            mapper.addBadges(builder, res2)
            builder.build()
        }
    }
}