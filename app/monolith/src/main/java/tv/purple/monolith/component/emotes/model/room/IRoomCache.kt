package tv.purple.monolith.component.emotes.model.room

interface IRoomCache {
    fun getGlobal(): IRoom

    fun getChannel(channelId: Int): IRoom

    fun fetch()

    fun refresh()

    fun rebuild()

    fun hasEmote(channelId: Int, code: String): Boolean
}