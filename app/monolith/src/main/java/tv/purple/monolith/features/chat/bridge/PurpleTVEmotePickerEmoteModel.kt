package tv.purple.monolith.features.chat.bridge

import tv.purple.monolith.models.wrapper.EmotePackageSet
import tv.twitch.android.models.emotes.EmoteModel
import tv.twitch.android.models.emotes.EmoteModelAssetType
import tv.twitch.android.models.emotes.EmoteModelType

class PurpleTVEmotePickerEmoteModel(
    emoteId: String?,
    emoteToken: String?,
    isAnimated: Boolean,
    val channelId: Int,
    val packageSet: EmotePackageSet,
    val isFavoriteEmote: Boolean = false
) : EmoteModel.Generic(
    emoteId,
    emoteToken,
    if (isAnimated) EmoteModelAssetType.ANIMATED else EmoteModelAssetType.STATIC,
    EmoteModelType.OTHER
)
