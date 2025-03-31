package tv.purple.monolith.core

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import tv.purple.monolith.core.models.ResIdCache
import tv.twitch.android.app.core.ApplicationContext
import tv.twitch.android.core.strings.StringResource

object ResManager {
    private val context: Context
        get() = ApplicationContext.getInstance().getContext()

    fun Context.fromResToDrawable(resName: String): Drawable {
        return getDrawable(this, getDrawableId(resName))
    }

    fun String.fromResToString(): String {
        return getString(this)
    }

    fun String.fromResToStringId(): Int {
        return getStringId(this)
    }

    fun String.fromResToDimenId(): Int {
        return getDimenId(this)
    }

    fun String.fromResToDrawableId(): Int {
        return getDrawableId(this)
    }

    fun String.fromResToColorId(): Int {
        return getColorId(this)
    }

    fun String.fromResToStringResource(): StringResource {
        return getStringResource(this)
    }

    fun String.fromResToLayoutId(): Int {
        return getLayoutId(this)
    }

    fun String.fromResToId(): Int {
        return getId(this)
    }

    fun String.fromResToString(vararg formatArgs: Any): String {
        return getString(this, *formatArgs)
    }

    private fun getDrawable(context: Context, id: Int): Drawable {
        return ContextCompat.getDrawable(
            context,
            id
        )!! // TODO: maybe check for null
    }

    private fun getId(resName: String): Int {
        return Id.ID.get(resName)
    }

    private fun getLayoutId(resName: String): Int {
        return Id.LAYOUT.get(resName)
    }

    private fun getStringId(resName: String): Int {
        return Id.STRING.get(resName)
    }

    private fun getStringResource(resName: String): StringResource {
        return StringResource.Companion!!.fromStringId(getStringId(resName = resName))
    }

    private fun getDrawableId(resName: String): Int {
        return Id.DRAWABLE.get(resName)
    }

    private fun getColorId(resName: String): Int {
        return Id.COLOR.get(resName)
    }

    private fun getColor(resName: String): Int {
        return ContextCompat.getColor(context, getColorId(resName))
    }

    private fun getDimenId(resName: String): Int {
        return Id.DIMEN.get(resName)
    }

    private fun getDimen(resName: String): Int {
        return context.resources.getDimension(getDimenId(resName)).toInt()
    }

    private fun getArrayId(resName: String): Int {
        return Id.ARRAY.get(resName)
    }

    private fun getAttrId(resName: String): Int {
        return Id.ATTR.get(resName)
    }

    private fun getString(resName: String, vararg formatArgs: Any): String {
        val id = Id.STRING.get(resName)

        return if (id == 0 || id == -1) {
            "\"$resName\": ID NOT FOUND"
        } else {
            context.getString(id, *formatArgs)
        }
    }

    private fun getString(resName: String): String {
        val id = Id.STRING.get(resName)

        return if (id == 0 || id == -1) {
            "\"$resName\": ID NOT FOUND"
        } else {
            context.getString(id)
        }
    }
}

private enum class Id(type: String, size: Int) {
    ID("id", 100),
    STRING("string", 100),
    ATTR("attr", 100),
    DRAWABLE("drawable", 10),
    COLOR("color", 10),
    LAYOUT("layout", 10),
    DIMEN("dimen", 10),
    ARRAY("array", 10);

    private val cache: ResIdCache = ResIdCache(
        type,
        size
    )

    fun get(resName: String): Int {
        return cache.get(resName)
    }
}