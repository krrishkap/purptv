package tv.purple.monolith.features.chat.bridge

import tv.purple.monolith.models.wrapper.EmotePackageSet
import tv.twitch.android.models.emotes.EmoteCardModel
import tv.twitch.android.models.emotes.EmoteModelAssetType

class PurpleTVEmoteCardModel(
    token: String,
    val url: String,
    val set: EmotePackageSet
) : EmoteCardModel(
    null,
    token,
    EmoteModelAssetType.UNKNOWN,
    PurpleTVEmoteCardType(),
    null
)