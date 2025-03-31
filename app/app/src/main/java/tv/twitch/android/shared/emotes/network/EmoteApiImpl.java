package tv.twitch.android.shared.emotes.network;

import io.reactivex.Single;
import tv.purple.monolith.features.chat.ChatHookProvider;
import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.emotes.EmoteCardModelResponse;

public class EmoteApiImpl {
    /* ... */

    public Single<EmoteCardModelResponse> getEmoteCardModelResponse(String emoteId) {
        EmoteCardModelResponse response = ChatHook.hookEmoteCardModelResponse(emoteId); // TODO: __INJECT_CODE
        if (response != null) { // TODO: __INJECT_CODE
            return Single.just(response);
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
