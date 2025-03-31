package tv.purple.monolith.component.emotes.model

data class Configuration(
    val bttv: Boolean = false,
    val ffz: Boolean = false,
    val stv: Boolean = false,
    val stvGlobal: Boolean = false,
    val homies: Boolean = false,
    val useWebp: Boolean = false
)