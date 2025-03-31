package tv.purple.monolith.features.refreshstream

import android.view.View
import android.widget.ImageView
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.twitch.android.shared.player.overlay.BottomPlayerControlOverlayViewDelegate

object ViewFactory {
    fun createRefreshStreamButton(delegate: BottomPlayerControlOverlayViewDelegate): ImageView {
        return delegate.contentView.getView(resName = RES_STRINGS.bottom_player_control_overlay_widget__refresh_button)
    }

    fun createRefreshStreamButton(view: View): ImageView {
        return view.getView(resName = RES_STRINGS.bottom_player_overlay_controls__refresh_button)
    }
}