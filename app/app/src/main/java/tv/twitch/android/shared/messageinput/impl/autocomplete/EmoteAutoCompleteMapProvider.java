package tv.twitch.android.shared.messageinput.impl.autocomplete;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.emotes.EmoteModel;
import tv.twitch.android.shared.emotes.emotepicker.IEmoteFetcher;
import tv.twitch.android.util.RxHelperKt;

public class EmoteAutoCompleteMapProvider {
    private final CompositeDisposable compositeDisposable = null;
    private final Map<String, EmoteModel> emoteNamesToEmotes = null;

    /* ... */

    public EmoteAutoCompleteMapProvider(IEmoteFetcher emoteFetcher) {
        /* ... */

        RxHelperKt.plusAssign(compositeDisposable, RxHelperKt.safeSubscribe(ChatHook.hookAutoCompleteMapProvider(emoteFetcher.getAllUserEmotesObservable()), null)); // TODO: __INJECT_CODE
        emoteNamesToEmotes.clear(); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
