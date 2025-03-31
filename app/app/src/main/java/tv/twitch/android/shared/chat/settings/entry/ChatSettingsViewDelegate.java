package tv.twitch.android.shared.chat.settings.entry;

import android.content.Context;
import android.view.View;

import java.util.List;

import io.reactivex.Flowable;
import tv.purple.monolith.bridge.PurpleTVAppContainer;
import tv.purple.monolith.features.chat.bridge.PurpleTVChatSettingsViewDelegate;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.mvp.viewdelegate.RxViewDelegate;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateEvent;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateState;

public class ChatSettingsViewDelegate extends RxViewDelegate {
    private final PurpleTVChatSettingsViewDelegate purpleTVViewDelegate; // TODO: __INJECT_FIELD

    /* ... */

    public ChatSettingsViewDelegate(Context context, View root) {
        super(null);
        /* ... */

        purpleTVViewDelegate = new PurpleTVChatSettingsViewDelegate(this, PurpleTVAppContainer.getChatHookProvider()); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    @Override
    public void render(ViewDelegateState viewDelegateState) {
        /* ... */

        purpleTVViewDelegate.render(viewDelegateState); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    public static abstract class ChatSettingsEvents implements ViewDelegateEvent {/* ... */
    }

    @Override
    public Flowable<ChatSettingsEvents> eventObserver() {
        /* ... */

        List listOf = null;

        listOf = purpleTVViewDelegate.injectEvents(listOf); // TODO: __INJECT_CODE

        return Flowable.merge(listOf);
    }

}
