package tv.twitch.android.shared.chat.moderation;

import androidx.fragment.app.FragmentActivity;

import tv.purple.monolith.features.logs.ChatLogs;
import tv.purple.monolith.models.exception.VirtualImpl;

public class ModerationActionBottomSheetPresenter {
    private FragmentActivity activity;

    /* ... */

    public final void requestAction(ModerationActionBottomSheetViewDelegate.ModerationActionButtonEvent moderationActionButtonEvent) {
//        if (ChatLogs.get().needShowModLogs(activity, moderationActionButtonEvent)) { // TODO: __INJECT_CODE
//            return;
//        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}