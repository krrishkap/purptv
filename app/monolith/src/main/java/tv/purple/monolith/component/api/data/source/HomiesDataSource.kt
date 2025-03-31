package tv.purple.monolith.component.api.data.source

import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.HomiesApi
import tv.purple.monolith.component.api.data.api.NopApi
import tv.purple.monolith.component.api.data.mapper.HomiesMapper
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.data.emotes.EmoteSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomiesDataSource @Inject constructor(
    private val api: HomiesApi,
    private val api2: NopApi,
    private val mapper: HomiesMapper
) {
    private var globalCache: EmoteSet = EmoteSet.EMPTY
    private val channelsCache: android.util.LruCache<Int, EmoteSet> = android.util.LruCache(1)

    fun setGlobalSetToCache(set: EmoteSet) {
        globalCache = set
    }

    fun getGlobalSetFromCache(): EmoteSet {
        return globalCache
    }

    fun saveChannelToCache(channelId: Int, set: EmoteSet) {
        channelsCache.put(channelId, set)
    }

    fun getChannelFromCache(channelId: Int): EmoteSet? {
        return channelsCache[channelId]
    }

    fun getGlobalEmotes(): Single<EmoteSet> {
        return api.getEmotes().map {
            mapper.map(it.data.global_emotes)
        }
    }

    fun getChannelEmotes(channelId: Int): Single<EmoteSet> {
        return api.getEmotes().map {
            mapper.map(it.data.channel_emotes, channelId)
        }
    }

    fun getItzBadges(): Single<BadgeSet> {
        return Single.zip(
            api.getBadges(),
            api.getBadges2(),
            api2.getHomiesBadges()
        ) { res1, res2, res3 ->
            val builder = BadgeSet.Builder()
            mapper.addBadges(builder, res1)
            mapper.addBadges(builder, res2)
            mapper.addBadges(builder, res3)
            builder.build()
        }
    }
}