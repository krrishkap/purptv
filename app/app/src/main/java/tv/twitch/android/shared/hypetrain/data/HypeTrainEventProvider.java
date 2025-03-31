package tv.twitch.android.shared.hypetrain.data;

import io.reactivex.Flowable;
import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.shared.hypetrain.network.pubsub.HypeTrainPubSubEvent;


public class HypeTrainEventProvider {
    /* ... */

    public final void bind(int i) {
        if (ChatHook.getHypetrainDisabled()) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */

        throw new VirtualImpl();
    }

    private final Flowable<HypeTrainPubSubEvent> registerPubSubObserver() {
        if (ChatHook.getHypetrainDisabled()) { // TODO: __INJECT_CODE
            return Flowable.empty();
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}