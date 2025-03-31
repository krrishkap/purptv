package tv.purple.monolith.component.emotes.model.room

import android.util.LruCache
import tv.purple.monolith.component.emotes.model.Configuration
import tv.purple.monolith.component.emotes.model.ConfigurationProvider
import tv.purple.monolith.component.emotes.model.factory.IRoomFactory

class RoomCache(
    private val roomFactory: IRoomFactory,
    size: Int
) : LruCache<Int, IRoom>(size), IRoomCache, ConfigurationProvider.ConfigurationListener {
    private var globalRoom: IRoom? = null
    private var configuration: Configuration = Configuration()

    override fun entryRemoved(
        evicted: Boolean,
        key: Int?,
        oldValue: IRoom?,
        newValue: IRoom?
    ) {
        super.entryRemoved(evicted, key, oldValue, newValue)
        oldValue?.clear()
    }

    fun clear() {
        synchronized(this) {
            evictAll()
            if (globalRoom != null) {
                globalRoom?.clear()
                globalRoom = null
            }
        }
    }

    override fun create(channelId: Int): IRoom {
        return roomFactory.createChannelRoom(channelId, configuration)
    }

    override fun getGlobal(): IRoom {
        if (globalRoom == null) {
            synchronized(this) {
                if (globalRoom == null) {
                    globalRoom = roomFactory.createGlobalRoom(configuration)
                }
            }
        }

        return globalRoom!!
    }

    override fun getChannel(channelId: Int): IRoom {
        return get(channelId)
    }

    override fun fetch() {
        globalRoom?.fetch()
        snapshot().values.forEach { it.fetch() }
    }

    override fun refresh() {
        globalRoom?.refresh()
        snapshot().values.forEach { it.refresh() }
    }

    override fun rebuild() {
        synchronized(this) {
            val keys = snapshot().keys.toList()
            clear()
            globalRoom = roomFactory.createGlobalRoom(configuration)
            for (key in keys) {
                get(key)
            }
            fetch()
        }
    }

    override fun hasEmote(channelId: Int, code: String): Boolean {
        return getChannel(channelId).hasEmote(code) || globalRoom?.hasEmote(code) ?: false
    }

    override fun onConfigurationChanged(configuration: Configuration) {
        this.configuration = configuration
        rebuild()
    }
}