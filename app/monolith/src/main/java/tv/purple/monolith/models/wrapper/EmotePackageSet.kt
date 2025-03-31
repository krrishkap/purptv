package tv.purple.monolith.models.wrapper

import tv.purple.monolith.bridge.RES_STRINGS

enum class EmotePackageSet(
    val hash: String,
    val resName: String,
    val resTitleName: String? = null
) {
    Unknown(
        hash = "UNKNOWN",
        resName = RES_STRINGS.purpletv_emotecard_unknown,
        resTitleName = RES_STRINGS.purpletv_unknown_emotes
    ),
    TwitchChannel(
        hash = "TWITCH-CHANNEL",
        resName = RES_STRINGS.purpletv_emotecard_twitch_channel
    ),
    BttvChannel(
        hash = "BTTV-CHANNEL",
        resName = RES_STRINGS.purpletv_emotecard_bttv_channel,
        resTitleName = RES_STRINGS.purpletv_bttv_channel_emotes
    ),
    FfzChannel(
        hash = "FFZ-CHANNEL",
        resName = RES_STRINGS.purpletv_emotecard_ffz_channel,
        resTitleName = RES_STRINGS.purpletv_ffz_channel_emotes
    ),
    StvChannel(
        hash = "7TV-CHANNEL",
        resName = RES_STRINGS.purpletv_emotecard_stv_channel,
        resTitleName = RES_STRINGS.purpletv_stv_channel_emotes
    ),
    HomiesChannel(
        hash = "HOMIES-CHANNEL",
        resName = RES_STRINGS.purpletv_emotecard_homies_channel,
        resTitleName = RES_STRINGS.purpletv_homies_channel_emotes
    ),
    TwitchGlobal(
        hash = "TWITCH-GLOBAL",
        RES_STRINGS.purpletv_emotecard_twitch_global
    ),
    BttvGlobal(
        hash = "BTTV-GLOBAL",
        resName = RES_STRINGS.purpletv_emotecard_bttv_global,
        resTitleName = RES_STRINGS.purpletv_bttv_global_emotes
    ),
    FfzGlobal(
        hash = "FFZ-GLOBAL",
        resName = RES_STRINGS.purpletv_emotecard_ffz_global,
        resTitleName = RES_STRINGS.purpletv_ffz_global_emotes
    ),
    StvGlobal(
        hash = "7TV-GLOBAL",
        resName = RES_STRINGS.purpletv_emotecard_stv_global,
        resTitleName = RES_STRINGS.purpletv_stv_global_emotes
    ),
    HomiesGlobal(
        hash = "HOMIES-GLOBAL",
        resName = RES_STRINGS.purpletv_emotecard_homies_global,
        resTitleName = RES_STRINGS.purpletv_homies_global_emotes
    );

    companion object {
        fun from(name: String?): EmotePackageSet {
            return values().firstOrNull { it.name == name } ?: Unknown
        }

        fun fromHash(hash: String): EmotePackageSet {
            return values().firstOrNull { it.hash == hash } ?: Unknown
        }
    }
}