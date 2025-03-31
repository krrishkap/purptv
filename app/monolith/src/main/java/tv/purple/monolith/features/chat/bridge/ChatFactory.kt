package tv.purple.monolith.features.chat.bridge

import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.ResManager.fromResToDimenId
import tv.purple.monolith.core.ResManager.fromResToStringId
import tv.purple.monolith.models.data.emotes.Emote
import tv.purple.monolith.models.wrapper.EmotePackageSet
import tv.twitch.android.models.emotes.EmoteModelAssetType
import tv.twitch.android.shared.emotes.emotepicker.EmotePickerSource
import tv.twitch.android.shared.emotes.emotepicker.EmotePickerTrackingMetadata
import tv.twitch.android.shared.emotes.emotepicker.models.ClickedEmote
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteHeaderUiModel
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteImageDescriptor
import tv.twitch.android.shared.emotes.emotepicker.models.EmotePickerSection
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteUiModel
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteUiSet
import tv.twitch.android.shared.emotes.models.EmoteMessageInput

object ChatFactory {
    fun createFavEmoteUiModel(
        emoteId: String,
        emoteToken: String,
        isAnimated: Boolean,
        channelId: Int,
        packageSet: EmotePackageSet
    ): EmoteUiModel {
        val emoteMessageInput = EmoteMessageInput(emoteToken, emoteId, true)
        val picker = PurpleTVEmotePickerEmoteModel(
            emoteId = emoteId,
            emoteToken = emoteToken,
            isAnimated = isAnimated,
            channelId = channelId,
            packageSet = packageSet,
            isFavoriteEmote = true
        )
        return EmoteUiModel(
            emoteId,
            ClickedEmote.Unlocked(
                picker,
                emoteMessageInput,
                null,
                emptyList()
            ),
            if (isAnimated) EmoteModelAssetType.ANIMATED else EmoteModelAssetType.STATIC,
            if (isAnimated) EmoteImageDescriptor.ANIMATED else EmoteImageDescriptor.NONE,
            "emote_picker_emote_size".fromResToDimenId(),
            "emote_picker_emote_padding".fromResToDimenId()
        )
    }

    fun createFavEmoteUiSet(
        model: List<EmoteUiModel?>
    ): EmoteUiSet {
        val header = EmoteHeaderUiModel.EmoteHeaderStringResUiModel(
            RES_STRINGS.purpletv_emote_picker_fav_emotes.fromResToStringId(),
            true,
            EmotePickerSection.PURPLETV_FAV,
            false
        )
        return EmoteUiSet(header, model)
    }

    fun createPurpleTVFavEmoteUiModel(
        url: String,
        emoteCode: String,
        emoteId: String,
        channelId: Int,
        isAnimated: Boolean,
        packageSet: EmotePackageSet
    ): EmoteUiModel {
        val emoteMessageInput = EmoteMessageInput(emoteCode, emoteId, false)
        val emotePicker =
            PurpleTVEmotePickerEmoteModel(
                emoteId = emoteId,
                emoteToken = emoteCode,
                isAnimated = isAnimated,
                channelId = channelId,
                packageSet = packageSet,
                isFavoriteEmote = true
            )
        val clickedEmote = ClickedEmote.Unlocked(
            emotePicker,
            emoteMessageInput,
            null,
            emptyList()
        )
        return PurpleTVEmoteUiModelWithStaticUrl(emoteId, clickedEmote, url, isAnimated)
    }

    fun createEmoteUiModel(
        emote: Emote,
        channelId: Int,
        isAnimated: Boolean,
        packageSet: EmotePackageSet
    ): EmoteUiModel {
        val emoteMessageInput = EmoteMessageInput(emote.getCode(), "-1", false)
        val emotePicker = PurpleTVEmotePickerEmoteModel(
            emoteId = "0",
            emoteToken = emote.getCode(),
            isAnimated = isAnimated,
            channelId = channelId,
            packageSet = packageSet
        )
        val clickedEmote = ClickedEmote.Unlocked(
            emotePicker,
            emoteMessageInput,
            EmotePickerTrackingMetadata(
                "0",
                "0",
                0,
                channelId,
                EmotePickerSource.Chat
            ),
            emptyList()
        )
        return PurpleTVEmoteUiModelWithStaticUrl(
            clickedEmote = clickedEmote,
            url = emote.getUrl(Emote.Size.LARGE),
            isAnimated = isAnimated
        )
    }
}