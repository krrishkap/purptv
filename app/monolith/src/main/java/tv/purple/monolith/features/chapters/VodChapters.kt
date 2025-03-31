package tv.purple.monolith.features.chapters

import android.view.View
import android.widget.ImageView
import tv.purple.monolith.features.chapters.data.view.ChaptersFragment
import tv.purple.monolith.features.chapters.view.ViewFactory
import tv.twitch.android.shared.player.overlay.PlayerOverlayViewDelegate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VodChapters @Inject constructor(
    private val viewFactory: ViewFactory
) {
    companion object {
        fun fixVodId(vodId: String): String {
            return if (vodId.startsWith("v")) {
                vodId.substring(1)
            } else {
                vodId
            }
        }
    }

    fun createChaptersButton(view: View): ImageView? {
        return viewFactory.createChaptersButton(view = view)
    }

    fun getChaptersFragment(): ChaptersFragment {
        return viewFactory.getChaptersFragment()
    }

    fun getChaptersButton(delegate: PlayerOverlayViewDelegate): ImageView? {
        return viewFactory.getChaptersButton(delegate)
    }
}