package tv.twitch.android.shared.emotes.emotepicker;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.features.chat.bridge.PurpleTVScrollableSection;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.adapters.RecyclerAdapterSection;
import tv.twitch.android.core.adapters.TwitchSectionAdapter;
import tv.twitch.android.shared.emotes.emotepicker.adapter.EmotePickerAdapterSection;
import tv.twitch.android.shared.emotes.emotepicker.models.EmotePickerSection;
import tv.twitch.android.shared.ui.elements.list.ContentListViewDelegate;

public class EmotePickerViewDelegate implements PurpleTVScrollableSection { // TODO: __IMPLEMENT
    private final ContentListViewDelegate listViewDelegate = null;
    private final ImageView purpleTVEmotesButton; // TODO: __INJECT_FIELD

    public EmotePickerViewDelegate() {

        /* ... */

        purpleTVEmotesButton = ChatHook.getPurpleTVEmotesButton(this); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    public void render(EmotePickerPresenter.EmotePickerState state) {
        ChatHook.renderEmotePickerState(state, purpleTVEmotesButton); // TODO: __INJECT_CODE
    }

    public void scrollToPurpleTVSection() { // TODO: __INJECT_METHOD
        if (listViewDelegate == null) {
            return;
        }

        TwitchSectionAdapter adapter = (TwitchSectionAdapter) listViewDelegate.getAdapter();

        if (adapter == null) {
            return;
        }

        List<RecyclerAdapterSection> sections = adapter.getSections();
        if (sections == null || sections.isEmpty()) {
            return;
        }

        int pos = 0;

        int sizeWithHeader = 0;
        for (RecyclerAdapterSection t : sections) {
            EmotePickerAdapterSection section = (EmotePickerAdapterSection) t;

            if (section.getEmotePickerSection() == EmotePickerSection.PURPLETV) {
                pos = sizeWithHeader;
                break;
            }

            sizeWithHeader += section.sizeWithHeader();
        }

        listViewDelegate.fastScrollToPosition(pos);
    }

    public static final class Companion {
        public final EmotePickerViewDelegate create(Context context, ViewGroup.LayoutParams layoutParams, Object config) {
            if (ChatHook.isStickyHeadersEnabled()) { // TODO: __INJECT_CODE
                // uContentList.enableStickyHeaders();
            }

            throw new VirtualImpl();
        }
    }
}