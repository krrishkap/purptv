package tv.purple.monolith.models.data.badges.impl

import android.graphics.Color
import tv.purple.monolith.models.data.badges.Badge

data class BadgeImpl(
    private val code: String,
    private val url: String,
    private val backgroundColor: Int = Color.TRANSPARENT,
    private val replaces: String? = null
) : Badge {
    override fun getCode(): String = code
    override fun getUrl(): String = url
    override fun getBackgroundColor(): Int = backgroundColor
    override fun getReplaces(): String? = replaces
}