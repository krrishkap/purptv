package tv.twitch.android.shared.hypetrain.data;

import io.reactivex.Flowable;
import io.reactivex.Single;
import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.shared.hypetrain.network.pubsub.HypeTrainPubSubEvent;
import tv.twitch.android.shared.hypetrain.pub.models.HypeTrainResponse;


public class HypeTrainDataSource {
    /* ... */

    public Single<HypeTrainResponse> fetchHypeTrain(int i) {
        if (ChatHook.getHypetrainDisabled()) { // TODO: __INJECT_CODE
            return Single.just(HypeTrainResponse.None.INSTANCE);
        }

        /* ... */

        throw new VirtualImpl();
    }

    public Flowable<HypeTrainPubSubEvent> hypeTrainPubSubEventObserver(final int i) {
        if (ChatHook.getHypetrainDisabled()) { // TODO: __INJECT_CODE
            return Flowable.empty();
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}