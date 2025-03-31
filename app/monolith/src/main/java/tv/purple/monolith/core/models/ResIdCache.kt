package tv.purple.monolith.core.models

import android.annotation.SuppressLint
import android.util.LruCache
import tv.twitch.android.app.core.ApplicationContext
import javax.inject.Inject


class ResIdCache @Inject constructor(
    private val resType: String,
    cacheSize: Int,
) : LruCache<String, Int>(cacheSize) {
    @SuppressLint("DiscouragedApi")
    override fun create(resName: String): Int {
        val context = ApplicationContext.getInstance().getContext()
        return context.resources.getIdentifier(resName, resType, context.packageName)
    }
}