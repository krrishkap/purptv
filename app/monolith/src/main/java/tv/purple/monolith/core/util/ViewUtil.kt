package tv.purple.monolith.core.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tv.purple.monolith.core.ResManager.fromResToId
import tv.purple.monolith.core.ResManager.fromResToLayoutId

object ViewUtil {
    fun <T : View> View.getView(resName: String): T {
        val resId = resName.fromResToId()
        if (!isValidId(resId)) {
            throw Resources.NotFoundException(resName)
        }
        return findViewById(resId)
    }

    fun <T : View> View.getViewIfExists(resName: String): T? {
        val resId = resName.fromResToId()
        if (!isValidId(resId)) {
            return null
        }
        return findViewById(resId)
    }

    fun <T : View> Activity.getView(resName: String): T {
        val resId = resName.fromResToId()
        if (!isValidId(resId)) {
            throw Resources.NotFoundException(resName)
        }
        return findViewById(resId)
    }

    fun Activity.setContentView(resName: String) {
        val resId = resName.fromResToLayoutId()
        if (!isValidId(resId)) {
            throw Resources.NotFoundException(resName)
        }
        setContentView(resId)
    }

    fun View.inflate(
        resName: String,
        parent: ViewGroup? = null,
        attachToRoot: Boolean = false
    ): View {
        val id = resName.fromResToLayoutId()
        if (!isValidId(id)) {
            throw Resources.NotFoundException(resName)
        }

        return LayoutInflater.from(context).inflate(id, parent, attachToRoot)
    }

    fun Context.inflate(
        resName: String,
        parent: ViewGroup? = null,
        attachToRoot: Boolean = false
    ): View {
        val id = resName.fromResToLayoutId()
        if (!isValidId(id)) {
            throw Resources.NotFoundException(resName)
        }

        return LayoutInflater.from(this).inflate(id, parent, attachToRoot)
    }

    fun View.changeVisibility(isVisible: Boolean) {
        visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun show(vararg views: View) {
        views.forEach { it.changeVisibility(true) }
    }

    fun hide(vararg views: View) {
        views.forEach { it.changeVisibility(false) }
    }

    fun hide1(vararg views: View) {
        views.forEach { it.visibility = View.INVISIBLE }
    }

    fun ViewGroup.isHit(rect: Rect, x: Int, y: Int): Boolean {
        getHitRect(rect)
        return rect.contains(x, y)
    }

    fun View.isVisible(): Boolean = visibility == View.VISIBLE

    fun Context.dipToPix(dip: Int): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dip.toFloat(),
        resources.displayMetrics
    ).toInt()

    fun Context.spToPx(sp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp.toFloat(),
            resources.displayMetrics
        )
    }

    fun Context.dipToPix(dip: Float): Int = dipToPix(dip.toInt())

    fun View.dipToPix(dip: Int): Int = context.dipToPix(dip)

    fun isValidId(res: Int): Boolean = res != View.NO_ID && res != 0

    fun Context.isLandscapeOrientation(): Boolean =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}