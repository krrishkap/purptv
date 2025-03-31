package tv.purple.monolith.models.retrofit.stv.v3

data class EmotePartial(
    val id: String,
    val name: String,
    val flags: Int,
    val host: ImageHost,
    val animated: Boolean,
    val lifecycle: EmoteLifecycle
)
