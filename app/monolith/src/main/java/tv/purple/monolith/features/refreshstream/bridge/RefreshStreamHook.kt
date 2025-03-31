package tv.purple.monolith.features.refreshstream.bridge

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import tv.purple.monolith.bridge.ex.IBottomPlayerControlOverlayViewDelegate
import tv.purple.monolith.bridge.ex.IRxBottomPlayerControlOverlayViewDelegate
import tv.purple.monolith.core.LoggerImpl
import tv.purple.monolith.core.compat.ClassCompat
import tv.purple.monolith.core.compat.ClassCompat.getPrivateField
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.util.ViewUtil.changeVisibility
import tv.purple.monolith.features.refreshstream.ViewFactory
import tv.twitch.android.core.data.source.DataProvider
import tv.twitch.android.core.data.source.SharedEventDispatcher
import tv.twitch.android.feature.theatre.refactor.TheatreViewCoordinatorPresenter
import tv.twitch.android.shared.player.overlay.BottomPlayerControlOverlayViewDelegate
import tv.twitch.android.shared.theatre.data.pub.model.TheatreCoordinatorRequest

object RefreshStreamHook {
    @JvmStatic
    fun tryPushRefreshRequest(presenter: TheatreViewCoordinatorPresenter) {
        LoggerImpl.debug("tryPushRefreshRequest: $presenter")
        (presenter.getPrivateField<DataProvider<TheatreCoordinatorRequest>>("theatreCoordinatorRequestProvider") as SharedEventDispatcher).pushUpdate(
            TheatreCoordinatorRequest.RetryVideoPlay.INSTANCE
        )
    }

    @JvmStatic
    fun getRefreshStreamButton(delegate: BottomPlayerControlOverlayViewDelegate): ImageView {
        return ViewFactory.createRefreshStreamButton(delegate = delegate).apply {
            setOnClickListener {
                ClassCompat.invokeIf<IBottomPlayerControlOverlayViewDelegate>(delegate) {
                    it.onRefreshStreamClicked()
                }
            }
            changeVisibility(Flag.SHOW_REFRESH_BUTTON.asBoolean())
        }
    }

    @JvmStatic
    fun getRefreshStreamButton(view: View): ImageView {
        return ViewFactory.createRefreshStreamButton(view = view).apply {
//            changeVisibility(Flag.SHOW_REFRESH_BUTTON.asBoolean())
            changeVisibility(false)
        }
    }

    @JvmStatic
    fun bind(
        rxBottomPlayerControlOverlayViewDelegate: IRxBottomPlayerControlOverlayViewDelegate,
        refreshStreamButton: ImageView?
    ) {
        refreshStreamButton?.setOnClickListener {
            Handler(Looper.getMainLooper()).post {
                rxBottomPlayerControlOverlayViewDelegate.pushRefreshEvent()
                LoggerImpl.debug("event: pushed refresh event")
            }
        }
    }
}