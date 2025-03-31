package tv.twitch.android.shared.chat.emotecard;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import tv.purple.monolith.features.chat.bridge.PurpleTVEmoteCardModel;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.emotes.EmoteCardModel;
import tv.twitch.android.models.emotes.EmoteCardModelResponse;

public class EmoteCardPresenter$bind$1 implements Function1<EmoteCardModelResponse, Unit> {
    EmoteCardPresenter this$0;

    /* ... */

    @Override
    public Unit invoke(EmoteCardModelResponse emoteCardModelResponse) {
        if (emoteCardModelResponse instanceof EmoteCardModelResponse.Success) {
            EmoteCardModel emoteCardModel = ((EmoteCardModelResponse.Success) emoteCardModelResponse).getEmoteCardModel();
            if (emoteCardModel instanceof PurpleTVEmoteCardModel) { // TODO: __INJECT_CODE
                if (this$0 != null) {
                    this$0.pushPurpleTVEmoteCardLoadedState((PurpleTVEmoteCardModel) emoteCardModel);
                    return Unit.INSTANCE;
                }
            }
        }

        throw new VirtualImpl();
    }

    /* ... */
}
