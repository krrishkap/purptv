package tv.purple.monolith.bridge.ex

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout

interface IPlayerWrapper {
    fun getContext(): Context
    fun getPlayerOverlayContainer(): ViewGroup
    fun getDebugPanelContainer(): ViewGroup
    fun getChatWrapper(): FrameLayout?
    fun getOneChatOverlayContainer(): FrameLayout?
}