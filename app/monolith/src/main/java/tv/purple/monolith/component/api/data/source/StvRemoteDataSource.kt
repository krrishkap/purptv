package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.StvApi
import tv.purple.monolith.component.api.data.api.StvOldApi
import tv.purple.monolith.component.api.data.mapper.StvApiMapper
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.data.emotes.EmoteSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StvRemoteDataSource @Inject constructor(
    private val api: StvApi,
    private val oldApi: StvOldApi,
    private val mapper: StvApiMapper,
    private val scheduler: Scheduler
) {
    fun getGlobalEmotes(): Single<EmoteSet> {
        return api.globalEmotes()
            .subscribeOn(scheduler)
            .map(mapper::mapGlobalEmotes)
            .map { EmoteSet(it) }
    }

    fun getChannelEmotes(channelId: Int): Single<EmoteSet> {
        return api.user(channelId)
            .subscribeOn(scheduler)
            .map { it.emote_set }
            .map(mapper::mapChannelEmotes)
            .map { EmoteSet(it) }
            .onErrorReturnItem(EmoteSet.EMPTY)
    }

    fun getChannelEmoteSetId(channelId: Int): Single<String?> {
        return api.user(channelId)
            .subscribeOn(scheduler)
            .map { it.emote_set.id }
    }

    fun getBadges(): Single<BadgeSet> {
        return oldApi.badges()
            .subscribeOn(scheduler)
            .map(mapper::mapBadges)
    }
}