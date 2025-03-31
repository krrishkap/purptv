package tv.purple.monolith.models.retrofit.homies

import tv.purple.monolith.models.retrofit.homies.ChannelEmotes
import tv.purple.monolith.models.retrofit.homies.Emote

data class EmotesData(
    val global_emotes: List<Emote>,
    val channel_emotes: Map<String, ChannelEmotes>
)
