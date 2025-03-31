package tv.purple.monolith.features.chat.bridge

import tv.purple.monolith.models.data.emotes.Emote
import tv.purple.monolith.models.wrapper.EmoteCardModelWrapper
import tv.twitch.android.shared.chat.pub.messages.data.MessageTokenV2

class PurpleTVEmoteToken(
    val emote: Emote,
    emoteSize: Emote.Size
) : MessageTokenV2.EmoteToken(
    EmoteCardModelWrapper(
        token = emote.getCode(),
        url = emote.getUrl(Emote.Size.LARGE),
        emote.getPackageSet()
    ).toJsonString(),
    emote.getCode(),
    emote.getUrl(emoteSize)
)