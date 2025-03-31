package tv.purple.monolith.component.emotes.model

import tv.purple.monolith.models.data.emotes.Emote
import tv.purple.monolith.models.wrapper.EmotePackageSet

interface EmotePackage {
    fun getEmote(code: String): Emote?

    fun getEmotes(): Collection<Emote>

    fun refresh(forced: Boolean)

    fun isEmpty(): Boolean

    fun getToken(): EmotePackageSet

    fun clear()

    fun hasEmote(code: String): Boolean
}