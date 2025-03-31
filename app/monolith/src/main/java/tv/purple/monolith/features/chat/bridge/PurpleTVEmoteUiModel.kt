package tv.purple.monolith.features.chat.bridge

import tv.purple.monolith.core.ResManager.fromResToDimenId
import tv.twitch.android.models.emotes.EmoteModelAssetType
import tv.twitch.android.shared.emotes.emotepicker.models.ClickedEmote
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteImageDescriptor
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteUiModel

sealed class PurpleTVEmoteUiModel(
    id: String?,
    clickedEmote: ClickedEmote?,
    isAnimated: Boolean
) : EmoteUiModel(
    id,
    clickedEmote,
    if (isAnimated) EmoteModelAssetType.ANIMATED else EmoteModelAssetType.STATIC,
    if (isAnimated) EmoteImageDescriptor.ANIMATED else EmoteImageDescriptor.NONE,
    "emote_picker_emote_size".fromResToDimenId(),
    "emote_picker_emote_padding".fromResToDimenId()

)

class PurpleTVEmoteUiModelWithStaticUrl(
    id: String? = "0",
    clickedEmote: ClickedEmote?,
    val url: String,
    isAnimated: Boolean
) : PurpleTVEmoteUiModel(id, clickedEmote, isAnimated)