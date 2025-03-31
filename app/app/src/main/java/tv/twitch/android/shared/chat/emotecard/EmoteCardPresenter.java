package tv.twitch.android.shared.chat.emotecard;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.features.chat.bridge.PurpleTVEmoteCardModel;
import tv.twitch.android.core.mvp.presenter.RxPresenter;
import tv.twitch.android.core.mvp.presenter.StateUpdateEvent;

public class EmoteCardPresenter extends RxPresenter<EmoteCardState, EmoteCardViewDelegate> {
    private final EmoteCardPresenter$stateUpdater$1 stateUpdater = null;

    /* ... */

    public void pushPurpleTVEmoteCardLoadedState(PurpleTVEmoteCardModel emoteCardModel) { // TODO: __INJECT_METHOD
        ChatHook.pushPurpleTVEmoteCardLoadedState(stateUpdater, emoteCardModel);
    }

    public static abstract class UpdateEvent implements StateUpdateEvent {
        /* ... */
    }
    /* ... */
}