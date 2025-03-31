package tv.twitch.android.shared.chat.chatheader;

import io.reactivex.Flowable;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import tv.purple.monolith.features.ui.UI;
import tv.purple.monolith.features.ui.bridge.HideKeyboardActionButtonClicked;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.mvp.presenter.PresenterState;
import tv.twitch.android.core.mvp.presenter.RxPresenter;
import tv.twitch.android.core.mvp.presenter.StateUpdateEvent;
import tv.twitch.android.core.mvp.rxutil.DisposeOn;
import tv.twitch.android.core.mvp.viewdelegate.EventDispatcher;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateEvent;

public class ChatHeaderPresenter extends RxPresenter<PresenterState, ChatHeaderViewDelegate> {
    private final EventDispatcher<Unit> hideInputEvents = new EventDispatcher<>(); // TODO: __INJECT_FIELD


    public final Flowable<Unit> observeHideInputButtonClickEvents() { // TODO: __INJECT_METHOD
        return hideInputEvents.eventObserver();
    }

    public void attach(ChatHeaderViewDelegate viewDelegate) {
        /* ... */

        setupOrangeStuff(viewDelegate); // TODO: __INJECT_CODE

        Event.ViewEvent event = null;
        if (event instanceof HideKeyboardActionButtonClicked) {
            return;
        }

        /* ... */
    }

    public static abstract class Event implements StateUpdateEvent {
        /* ... */

        public static abstract class ViewEvent extends Event implements ViewDelegateEvent {
            /* ... */

            public ViewEvent(DefaultConstructorMarker defaultConstructorMarker) {
                throw new VirtualImpl();
            }

            /* ... */
        }

        /* ... */
    }

    /* ... */

    private void setupOrangeStuff(ChatHeaderViewDelegate viewDelegate) { // TODO: __INJECT_METHOD
        directSubscribe(viewDelegate.eventObserver(), DisposeOn.VIEW_DETACHED, UI.dispatchHideKeyboard(hideInputEvents));
    }

    /* ... */
}