package tv.purple.monolith.features.chat.bridge

import tv.purple.monolith.core.ResManager.fromResToStringResource
import tv.twitch.android.shared.chat.emotecard.EmoteCardHeaderUiModel
import tv.twitch.android.shared.chat.emotecard.EmoteCardUiModel

class PurpleTVEmoteCardUiModel(
    model: PurpleTVEmoteCardModel
) : EmoteCardUiModel(
    EmoteCardHeaderUiModel(
        model.emoteToken,
        model.set.resName.fromResToStringResource(),
        model.url,
        false
    ),
    null,
    null,
    null,
    null,
    null,
    null
)