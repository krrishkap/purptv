package tv.purple.monolith.features.swipper.bridge

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import tv.purple.monolith.bridge.ex.IPlayerWrapper
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.util.ViewUtil.getView

class RefactorPlayerWrapper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), IPlayerWrapper, SwipperOverlay.Callback {
    private lateinit var playerOverlay: ViewGroup
    private lateinit var debugPanel: ViewGroup
    private lateinit var chatWrapper: FrameLayout
    private lateinit var oneChatOverlay: FrameLayout

    private val needInterceptTouchEvents get() = Flag.VOLUME_GESTURE.asBoolean() || Flag.BRIGHTNESS_GESTURE.asBoolean()

    private val playerWrapperDelegate = WrapperDelegate(
        wrapper = this
    )

    override fun onFinishInflate() {
        super.onFinishInflate()
        playerOverlay = getView("player_pane")
        debugPanel = getView("player_debug_stats_container")
        chatWrapper = getView("chat_wrapper")
        oneChatOverlay = getView("one_chat_overlay_container")
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

    override fun getChatWrapper(): FrameLayout {
        return chatWrapper
    }

    override fun getOneChatOverlayContainer(): FrameLayout {
        return oneChatOverlay
    }
}