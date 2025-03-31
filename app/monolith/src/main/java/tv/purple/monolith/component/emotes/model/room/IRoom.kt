package tv.purple.monolith.component.emotes.model.room

import tv.purple.monolith.models.wrapper.EmotePackageSet
import tv.purple.monolith.models.data.emotes.Emote

interface IRoom {
    fun getEmotes(): List<Emote>

    fun getEmotesMap(): List<Pair<EmotePackageSet, Collection<Emote>>>

    fun getEmote(code: String): Emote?

    fun getEmote(code: String, emotePackageSet: EmotePackageSet): Emote?

    fun fetch()

    fun refresh()

    fun clear()

    fun hasEmote(code: String): Boolean
}