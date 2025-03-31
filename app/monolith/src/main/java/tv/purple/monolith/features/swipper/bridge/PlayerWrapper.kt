package tv.purple.monolith.features.swipper.bridge

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import tv.purple.monolith.bridge.ex.IPlayerWrapper
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.util.ViewUtil.getView

class PlayerWrapper @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), IPlayerWrapper {
    lateinit var playerOverlay: ViewGroup
    lateinit var debugPanel: ViewGroup

    private val needInterceptTouchEvents = let {
        Flag.VOLUME_GESTURE.asBoolean() || Flag.BRIGHTNESS_GESTURE.asBoolean()
    }

    private val playerWrapperDelegate = WrapperDelegate(
        wrapper = this
    )

    override fun onFinishInflate() {
        super.onFinishInflate()
        playerOverlay = getView("player_overlay_container")
        debugPanel = getView("debug_panel_container")
        playerWrapperDelegate.setOverlayToPlayerContainer(viewGroup = playerOverlay)
        playerWrapperDelegate.isVolumeSwipeEnabled = Flag.VOLUME_GESTURE.asBoolean()
        playerWrapperDelegate.isBrightnessSwipeEnabled = Flag.BRIGHTNESS_GESTURE.asBoolean()
    }

    override fun onInterceptTouchEvent(motionEvent: MotionEvent): Boolean {
        return if (needInterceptTouchEvents) {
            playerWrapperDelegate.onInterceptTouchEvent(motionEvent = motionEvent)
        } else {
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        return playerWrapperDelegate.onTouchEvent(event = motionEvent)
    }

    override fun getPlayerOverlayContainer(): ViewGroup {
        return playerOverlay
    }

    override fun getDebugPanelContainer(): ViewGroup {
        return debugPanel
    }

    override fun getChatWrapper(): FrameLayout? {
        return null
    }

    override fun getOneChatOverlayContainer(): FrameLayout? {
        return null
    }
}