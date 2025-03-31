package tv.twitch.android.shared.player.overlay;

import android.view.View;

import tv.purple.monolith.bridge.ex.IBottomPlayerControlOverlayViewDelegate;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;

public class BottomPlayerControlOverlayViewDelegate extends BaseViewDelegate implements IBottomPlayerControlOverlayViewDelegate { // TODO: @features:refreshstream
    private final BottomPlayerControlListener mBottomPlayerControlListener;

    /* ... */

    public BottomPlayerControlOverlayViewDelegate(View view) {
        super(view);

        /* ... */

        throw new VirtualImpl();
    }

    public interface BottomPlayerControlListener {
        void onChangeOrientationButtonClicked();

        void onExpandVideoButtonClicked();

        void onMuteButtonClicked();

        void onShowChatButtonClicked();

        void onVideoDebugInfoButtonClicked();

        void onViewCountClicked();

        void onRefreshClicked(); // TODO: __INJECT_METHOD
    }

    /* ... */

    @Override
    public void onRefreshStreamClicked() { // TODO: __INJECT_METHOD
        mBottomPlayerControlListener.onRefreshClicked();
    }
}
