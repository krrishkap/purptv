package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.BttvApi
import tv.purple.monolith.component.api.data.mapper.BttvApiMapper
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.data.emotes.EmoteSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BttvRemoteDataSource @Inject constructor(
    private val api: BttvApi,
    private val mapper: BttvApiMapper,
    private val scheduler: Scheduler
) {
    fun getGlobalBttvEmotes(useWebp: Boolean): Single<EmoteSet> {
        return api.globalBttvEmotes()
            .subscribeOn(scheduler)
            .map { mapper.mapGlobalBttvEmotes(it, useWebp) }
            .map { EmoteSet(it) }
    }

    fun getChannelBttvEmotes(channelId: Int, useWebp: Boolean): Single<EmoteSet> {
        return api.bttvEmotes(channelId)
            .subscribeOn(scheduler)
            .map { mapper.mapChannelBttvEmotes(it, useWebp) }
            .map { EmoteSet(it) }
    }

    fun getGlobalFfzEmotes(): Single<EmoteSet> {
        return api.globalFfzEmotes()
            .subscribeOn(scheduler)
            .map(mapper::mapGlobalFfzEmotes)
            .map { EmoteSet(it) }
    }

    fun getChannelFfzEmotes(channelId: Int): Single<EmoteSet> {
        return api.ffzEmotes(channelId)
            .subscribeOn(scheduler)
            .map(mapper::mapChannelFfzEmotes)
            .map { EmoteSet(it) }
    }

    fun getBadges(): Single<BadgeSet> {
        return api.badges()
            .subscribeOn(scheduler)
            .map(mapper::map)
    }
}