package tv.twitch.android.shared.chat.creatorpinnedchatmessage.presenters;

import kotlin.Pair;
import tv.purple.monolith.features.chat.ChatHookProvider;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.shared.chat.creatorpinnedchatmessage.analytics.PinnedChatMessageUserRole;
import tv.twitch.android.shared.chat.pub.pinnedchat.CreatorPinnedChatUiModel;
import tv.twitch.android.util.Optional;

public class CreatorPinnedChatMessagePresenter$setupPinnedMessage$1 {
    /* ... */

    public final void invoke2(Pair<Optional<CreatorPinnedChatUiModel>, ? extends PinnedChatMessageUserRole> pair) {
        /* ... */

        CreatorPinnedChatUiModel creatorPinnedChatMessageModel = pair.component1().get();
        creatorPinnedChatMessageModel = ChatHookProvider.hookCreatorPinnedChatMessageModel(creatorPinnedChatMessageModel); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
