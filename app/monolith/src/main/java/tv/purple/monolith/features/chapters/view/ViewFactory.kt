package tv.purple.monolith.features.chapters.view

import android.view.View
import android.widget.ImageView
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.getViewIfExists
import tv.purple.monolith.features.chapters.data.view.ChaptersFragment
import tv.twitch.android.shared.player.overlay.PlayerOverlayViewDelegate
import javax.inject.Inject
import javax.inject.Provider

class ViewFactory @Inject constructor(
    val provider: Provider<ChaptersFragment>
) {
    fun createChaptersButton(view: View): ImageView? {
        return view.getViewIfExists(resName = RES_STRINGS.player_control_overlay_widget__purpletv_chapters_button)
    }

    fun getChaptersFragment(): ChaptersFragment {
        return provider.get()
    }

    fun getChaptersButton(delegate: PlayerOverlayViewDelegate): ImageView? {
        return delegate.contentView.getViewIfExists(resName = RES_STRINGS.player_control_overlay_widget__purpletv_chapters_button)
    }
}