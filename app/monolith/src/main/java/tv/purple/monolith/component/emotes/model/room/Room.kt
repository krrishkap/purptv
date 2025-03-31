package tv.purple.monolith.component.emotes.component.model.room

import tv.purple.monolith.component.emotes.model.EmotePackage
import tv.purple.monolith.component.emotes.model.room.IRoom
import tv.purple.monolith.models.data.emotes.Emote
import tv.purple.monolith.models.wrapper.EmotePackageSet

class Room(
    private val packages: List<EmotePackage>
) : IRoom {
    override fun getEmotes(): List<Emote> {
        return packages.flatMap { it.getEmotes() }
    }

    override fun getEmotesMap(): List<Pair<EmotePackageSet, Collection<Emote>>> {
        return packages.map { Pair(it.getToken(), it.getEmotes()) }
    }

    override fun getEmote(code: String): Emote? {
        packages.forEach { pack ->
            pack.getEmote(code)?.let { emote: Emote ->
                return emote
            }
        }

        return null
    }

    override fun getEmote(code: String, emotePackageSet: EmotePackageSet): Emote? {
        return packages.firstOrNull { it.getToken() == emotePackageSet }?.getEmote(code)
    }

    override fun fetch() {
        packages.forEach { pack ->
            pack.refresh(forced = true)
        }
    }

    override fun refresh() {
        packages.forEach { pack ->
            pack.refresh(forced = false)
        }
    }

    override fun clear() {
        packages.forEach { pack ->
            pack.clear()
        }
    }

    override fun hasEmote(code: String): Boolean {
        return packages.any {
            it.hasEmote(code)
        }
    }
}