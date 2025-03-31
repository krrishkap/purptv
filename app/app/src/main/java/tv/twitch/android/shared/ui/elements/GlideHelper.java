package tv.twitch.android.shared.ui.elements;

import android.content.Context;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;

import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.shared.ui.elements.span.GlideChatImageCustomTarget;
import tv.twitch.android.shared.ui.elements.span.GlideChatImageTarget;
import tv.twitch.android.shared.ui.elements.span.UrlDrawable;

public final class GlideHelper {
    public static final GlideHelper INSTANCE = new GlideHelper();

    /* ... */

    public final void clearPendingGlideLoad(Context context, Target<?> glideTarget) {
        throw new VirtualImpl();
    }

    private static GlideChatImageCustomTarget loadImageForUrlDrawableAngGetTarget(Context context, UrlDrawable urlDrawable, TextView textView) {
        GlideChatImageCustomTarget into = null;

        /* ... */

        into.setContainer(textView); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    private static void loadImageForUrlDrawable(Context context, UrlDrawable urlDrawable, TextView textView) {
        GlideChatImageTarget into = null;

        /* ... */

        into.setContainer(textView); // TODO: __INJECT_CODE // FIXME

        /* ... */

        throw new VirtualImpl();
    }

    public static final class RequestOptionsBundle {

    }

    /* ... */
}
