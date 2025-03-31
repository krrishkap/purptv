package tv.purple.monolith.features.swipper.bridge

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import tv.purple.monolith.core.LoggerImpl

class SwipperOverlay @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    var callback: Callback? = null

    override fun onInterceptTouchEvent(motionEvent: MotionEvent): Boolean {
        LoggerImpl.debug { "ev->$motionEvent" }
        callback?.let { c ->
            return c.onInterceptTouchEvent(motionEvent)
        }

        return super.onInterceptTouchEvent(motionEvent)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        LoggerImpl.debug { "ev->$motionEvent" }
        callback?.let { c ->
            return c.onTouchEvent(motionEvent)
        }

        return super.onTouchEvent(motionEvent)
    }

    interface Callback {
        fun onInterceptTouchEvent(motionEvent: MotionEvent): Boolean
        fun onTouchEvent(motionEvent: MotionEvent): Boolean
    }
}