package tv.purple.monolith.features.settings.bridge.model

import android.content.Context
import tv.purple.monolith.core.CoreUtil

sealed interface InfoItemBehavior {
    fun onClick(context: Context)

    class ClickableUrl(private val url: String) : InfoItemBehavior {
        override fun onClick(context: Context) {
            CoreUtil.openUrl(context, url)
        }
    }

    object None : InfoItemBehavior {
        override fun onClick(context: Context) {}
    }
}