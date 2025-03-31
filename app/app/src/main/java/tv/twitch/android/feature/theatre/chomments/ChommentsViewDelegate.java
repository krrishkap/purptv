package tv.twitch.android.feature.theatre.chomments;

import android.content.Context;
import android.view.View;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;

public final class ChommentsViewDelegate extends BaseViewDelegate {
    /* ... */

    public ChommentsViewDelegate(Context context, View view) {
        super(context, view);

        /* ... */

        ChatHook.maybeHideChatHeader(this); // TODO: __INJECT_CODE
    }

    /* ... */
}
