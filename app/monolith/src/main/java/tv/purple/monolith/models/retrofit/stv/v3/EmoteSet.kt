package tv.purple.monolith.models.retrofit.stv.v3

import tv.purple.monolith.models.retrofit.stv.v3.ActiveEmote

data class EmoteSet(
    val id: String,
    val emotes: List<ActiveEmote>
)
