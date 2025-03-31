package tv.purple.monolith.core.bridge.svg

import android.content.Context
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import tv.purple.monolith.core.util.ViewUtil.dipToPix
import tv.twitch.android.app.core.ApplicationContext
import tv.twitch.android.shared.ui.elements.span.`MediaSpan$Type`
import java.io.IOException
import java.io.InputStream


class SvgBadgeDecoder : ResourceDecoder<InputStream?, SVG?> {
    private val context: Context
        get() = ApplicationContext.getInstance().getContext()

    override fun handles(source: InputStream, options: Options): Boolean {
        return true
    }

    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options
    ): Resource<SVG?>? {
        return try {
            val svg = SVG.getFromInputStream(source)
            val badgeMediaTypeSize = `MediaSpan$Type`.Badge.sizeDp
            svg.documentWidth = if (width != SIZE_ORIGINAL) {
                width.toFloat()
            } else {
                context.dipToPix(badgeMediaTypeSize).toFloat()
            }
            svg.documentHeight = if (height != SIZE_ORIGINAL) {
                height.toFloat()
            } else {
                context.dipToPix(badgeMediaTypeSize).toFloat()
            }
            SimpleResource(svg)
        } catch (ex: SVGParseException) {
            throw IOException("Cannot load SVG from stream", ex)
        }
    }
}