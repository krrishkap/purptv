package tv.purple.monolith.features.chat.view

import android.widget.ImageView
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.compat.ClassCompat
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.features.chat.bridge.PurpleTVScrollableSection
import tv.twitch.android.shared.emotes.emotepicker.EmotePickerViewDelegate

object ViewFactory {
    fun createPurpleTVEmotesButton(delegate: EmotePickerViewDelegate): ImageView {
        return delegate.contentView.getView<ImageView>(resName = RES_STRINGS.emote_picker__purpletv_emotes_button)
            .apply {
                setOnClickListener {
                    ClassCompat.invokeIf<PurpleTVScrollableSection>(delegate) {
                        it.scrollToPurpleTVSection()
                    }
                }
            }
    }
}