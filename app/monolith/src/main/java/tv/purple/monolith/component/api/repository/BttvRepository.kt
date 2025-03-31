package tv.purple.monolith.component.api.repository

import io.reactivex.Single
import tv.purple.monolith.component.api.data.source.BttvRemoteDataSource
import tv.purple.monolith.core.LoggerImpl
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.data.emotes.EmoteSet
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BttvRepository @Inject constructor(
    private val bttvDataSource: BttvRemoteDataSource
) {
    fun getBttvGlobalEmotes(useWebp: Boolean): Single<EmoteSet> {
        return bttvDataSource.getGlobalBttvEmotes(useWebp)
    }

    fun getBttvChannelEmotes(channelId: Int, useWebp: Boolean): Single<EmoteSet> {
        return bttvDataSource.getChannelBttvEmotes(channelId = channelId, useWebp = useWebp)
            .onErrorResumeNext {
                LoggerImpl.warning("[BTTV] Cannot fetch channel emotes: $channelId")
                Single.just(EmoteSet.EMPTY)
            }
    }

    fun getFfzGlobalEmotes(): Single<EmoteSet> {
        return bttvDataSource.getGlobalFfzEmotes()
    }

    fun getFfzChannelEmotes(channelId: Int): Single<EmoteSet> {
        return bttvDataSource.getChannelFfzEmotes(channelId = channelId)
            .onErrorResumeNext {
                LoggerImpl.warning("[BTTV] Cannot fetch channel emotes: $channelId")
                Single.just(EmoteSet.EMPTY)
            }
    }

    fun getBttvBadges(): Single<BadgeSet> {
        return bttvDataSource.getBadges()
    }
}