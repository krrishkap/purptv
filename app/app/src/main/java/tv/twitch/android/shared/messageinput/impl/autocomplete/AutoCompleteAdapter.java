package tv.twitch.android.shared.messageinput.impl.autocomplete;

import android.view.View;
import android.view.ViewGroup;

import tv.purple.monolith.features.chat.bridge.PurpleTVEmoteModel;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.emotes.EmoteModel;

public class AutoCompleteAdapter {
    /* ... */

    public View getView(int i, View view, ViewGroup viewGroup) {
        EmoteModel model = null;

        if (model instanceof PurpleTVEmoteModel) { // TODO: __INJECT_CODE
            String url = ((PurpleTVEmoteModel) model).getEmoteUrl();
            url.toString();
        }

        throw new VirtualImpl();
    }

    /* ... */
}
