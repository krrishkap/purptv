package tv.purple.monolith.bridge.ex

import android.widget.ImageView

interface IRxBottomPlayerControlOverlayViewDelegate {
    fun pushRefreshEvent()

    fun getRefreshButton(): ImageView
}