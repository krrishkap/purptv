package tv.purple.monolith.component.emotes.model.factory

import tv.purple.monolith.component.emotes.model.Configuration
import tv.purple.monolith.component.emotes.model.room.IRoom

interface IRoomFactory {
    fun createChannelRoom(channelId: Int, configuration: Configuration): IRoom

    fun createGlobalRoom(configuration: Configuration): IRoom
}