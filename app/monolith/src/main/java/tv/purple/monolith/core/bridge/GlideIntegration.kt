package tv.purple.monolith.core.bridge

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.integration.webp.WebpGlideLibraryModule
import tv.purple.monolith.core.DebugLogger
import tv.purple.monolith.core.LoggerImpl
import tv.purple.monolith.core.bridge.svg.SvgModule
import tv.twitch.android.shared.ui.elements.image.TwitchGlideModule

object GlideIntegration {
    @JvmStatic
    fun registerWebpGlideLibraryModule(
        appGlideModule: TwitchGlideModule,
        context: Context,
        glide: Glide,
        registry: Registry
    ) {
        try {
            LoggerImpl.debug { "[Glide] Start webp integration..." }
            WebpGlideLibraryModule().registerComponents(context, glide, registry)
            LoggerImpl.debug { "[Glide] Done!" }

        } catch (th: Throwable) {
            DebugLogger.debugThrowable(th)
        }

        try {
            LoggerImpl.debug { "[Glide] Start svg integration..." }
            SvgModule().registerComponents(context, glide, registry)
            LoggerImpl.debug { "[Glide] Done!" }
        } catch (th: Throwable) {
            DebugLogger.debugThrowable(th)
        }
    }
}