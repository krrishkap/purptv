package tv.twitch.android.feature.theatre.refactor;

import androidx.constraintlayout.widget.ConstraintSet;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.features.ui.UI;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.feature.theatre.databinding.TheatreCoordinatorBinding;
import tv.twitch.android.shared.theatre.data.pub.model.TheatreViewState;

public class TheatreViewCoordinatorViewDelegate {
    private final TheatreCoordinatorBinding binding = null;

    /* ... */

    public TheatreViewCoordinatorViewDelegate(/* ... */) {
        /* ... */

        UI.changeLandscapeChatContainerOpacity(binding.chatWrapper); // TODO: __INJECT_CODE
    }

    private final void updateSplitConstraints(ConstraintSet constraintSet, TheatreViewState theatreViewState) {
        float split = ChatHook.getLandscapeSplitContainerScaleFloat();
        // constraintSet.setGuidelinePercent(this.binding.chatGuideline.getId(), 0.5f);

        float raw = ChatHook.getLandscapeContainerScaleFloat();
        // constraintSet.setGuidelinePercent(this.binding.chatGuideline.getId(), this.experience.shouldShowTabletUI(getContext()) ? 0.8f : 0.7f);

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
