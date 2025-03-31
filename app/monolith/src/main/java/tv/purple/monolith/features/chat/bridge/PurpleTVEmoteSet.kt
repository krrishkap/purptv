package tv.purple.monolith.features.chat.bridge

import tv.twitch.android.models.emotes.EmoteModel
import tv.twitch.android.models.emotes.EmoteSet

class PurpleTVEmoteSet(
    private val emotes: List<EmoteModel>
) : EmoteSet(null) {
    override fun getEmotes(): List<EmoteModel> {
        return emotes
    }

    override fun getSetId(): String {
        return "0"
    }
}