package tv.twitch.android.shared.chat.chatuserdialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import tv.purple.monolith.features.logs.ChatLogs;
import tv.purple.monolith.models.exception.VirtualImpl;

public class ChatUserDialogViewDelegate {
    private View root;

    /* ... */

    private final TextView localLogs; // TODO: __INJECT_FIELD

    /* ... */

    public ChatUserDialogViewDelegate(Context context, View view, Object optionsToShow) {
        /* ... */

        // localLogs = ChatLogs.get().injectLocalLogsButton(root);  // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    /* ... */

    public final TextView getLocalLogs() {  // TODO: __INJECT_METHOD
        return this.localLogs;
    }
}
