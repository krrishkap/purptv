package tv.purple.monolith.component.emotes

import tv.purple.monolith.component.emotes.model.Configuration
import tv.purple.monolith.component.emotes.model.ConfigurationProvider
import tv.purple.monolith.component.emotes.model.factory.RoomFactory
import tv.purple.monolith.component.emotes.model.room.RoomCache
import tv.purple.monolith.core.Config
import tv.purple.monolith.models.data.emotes.Emote
import tv.purple.monolith.models.wrapper.EmotePackageSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmoteProvider @Inject constructor(
    roomFactory: RoomFactory,
    config: Config
) : ConfigurationProvider {
    private val cache = RoomCache(
        roomFactory = roomFactory,
        size = config.MAX_ROOM_CACHE_SIZE
    )

    private var configuration: Configuration = Configuration()

    fun hasEmote(channelId: Int, code: String): Boolean {
        return cache.hasEmote(channelId, code)
    }

    fun getEmotesMap(channelId: Int): List<Pair<EmotePackageSet, Collection<Emote>>> {
        return cache.run {
            getChannel(channelId).getEmotesMap() + getGlobal().getEmotesMap()
        }
    }

    fun getEmote(code: String, channelId: Int): Emote? {
        return cache.run {
            getChannel(channelId).getEmote(
                code = code
            ) ?: getGlobal().getEmote(
                code = code
            )
        }
    }

    fun getEmoteFromPackageSet(
        code: String,
        channelId: Int,
        emotePackageSet: EmotePackageSet
    ): Emote? {
        return cache.run {
            getChannel(channelId).getEmote(
                code = code,
                emotePackageSet = emotePackageSet
            ) ?: getGlobal().getEmote(
                code = code
            )
        }
    }

    fun refreshEmotes(channelId: Int) {
        cache.getChannel(channelId).refresh()
        cache.getGlobal().refresh()
    }

    fun refresh() {
        cache.refresh()
    }

    fun clear() {
        cache.clear()
    }

    fun fetch() {
        cache.fetch()
    }

    fun rebuild() {
        cache.rebuild()
    }

    override fun getConfiguration(): Configuration {
        return configuration
    }

    override fun setConfiguration(configuration: Configuration) {
        this.configuration = configuration
        cache.onConfigurationChanged(configuration)
    }
}