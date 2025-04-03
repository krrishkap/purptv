@file:Suppress("KotlinJniMissingFunction")

package tv.purple.monolith.features.vodhunter

import com.getkeepsafe.relinker.ReLinker
import tv.twitch.android.app.core.ApplicationContext

object LibWrapper {
    private const val LIB_VERSION = "0.1"

    external fun getVodHunterPayload(stream: String): String

    init {
        ReLinker.loadLibrary(ApplicationContext.getInstance().getContext(), "monolith", LIB_VERSION)
    }
}