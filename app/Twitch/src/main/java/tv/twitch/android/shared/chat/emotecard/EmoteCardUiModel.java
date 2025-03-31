package tv.twitch.android.shared.chat.emotecard;

import kotlin.NotImplementedError;
import tv.twitch.android.core.strings.StringResource;

public class EmoteCardUiModel { // TODO: __REMOVE_FINAL
    /* ... */

    public EmoteCardUiModel(EmoteCardHeaderUiModel header, StringResource stringResource, RelatedEmotesUiModel relatedEmotesUiModel, ChannelButtonUiModel channelButtonUiModel, FollowButtonUiModel followButtonUiModel, SubscribeButtonUiModel subscribeButtonUiModel, ReportButtonUiModel reportButtonUiModel) {
        throw new NotImplementedError();
    }

    /* ... */

    public final FollowButtonUiModel getFollowButton() {
        throw new NotImplementedError();
    }

    /* ... */
}
