package tv.purple.monolith.component.api.repository

import io.reactivex.Single
import tv.purple.monolith.component.api.data.source.HomiesDataSource
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.data.emotes.EmoteSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomiesRepository @Inject constructor(
    private val dataSource: HomiesDataSource
) {
    fun getGlobalEmotes(): Single<EmoteSet> {
        val globalCache = dataSource.getGlobalSetFromCache()
        if (globalCache != EmoteSet.EMPTY) {
            return Single.just(globalCache)
        }

        return dataSource.getGlobalEmotes().doOnSuccess {
            dataSource.setGlobalSetToCache(it)
        }
    }

    fun getChannelEmotes(channelId: Int): Single<EmoteSet> {
        dataSource.getChannelFromCache(channelId)?.let {
            return Single.just(it)
        }

        return dataSource.getChannelEmotes(channelId).doOnSuccess {
            dataSource.saveChannelToCache(channelId, it)
        }
    }

    fun getBadges(): Single<BadgeSet> {
        return dataSource.getItzBadges()
    }
}