package tv.purple.monolith.models.data.emotes


class EmoteSet(
    emotes: Collection<Emote>
) {
    private val map = LinkedHashMap<String, Emote>(emotes.size).apply {
        emotes.forEach { emote ->
            put(emote.getCode(), emote)
        }
    }

    fun getEmote(code: String): Emote? {
        return map[code]
    }

    fun getEmotes(): Collection<Emote> {
        return map.values
    }

    fun isEmpty(): Boolean {
        return map.isEmpty()
    }

    override fun toString(): String {
        return "EmoteSet(emotes=${map.size})"
    }

    fun hasEmote(code: String): Boolean {
        return map.containsKey(code)
    }

    companion object {
        val EMPTY = EmoteSet(emptyList())
    }
}