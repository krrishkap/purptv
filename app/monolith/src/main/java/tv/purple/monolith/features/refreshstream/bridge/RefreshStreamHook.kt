package tv.purple.monolith.features.refreshstream.bridge

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import tv.purple.monolith.bridge.ex.IRxBottomPlayerControlOverlayViewDelegate
import tv.purple.monolith.core.LoggerWithTag
import tv.purple.monolith.core.compat.ClassCompat.getPrivateField
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.util.ViewUtil.changeVisibility
import tv.purple.monolith.features.refreshstream.ViewFactory
import tv.twitch.android.core.data.source.DataProvider
import tv.twitch.android.core.data.source.DataUpdater
import tv.twitch.android.core.data.source.SharedEventDispatcher
import tv.twitch.android.feature.theatre.refactor.TheatreViewCoordinatorPresenter
import tv.twitch.android.shared.theatre.data.pub.model.TheatreCoordinatorRequest

object RefreshStreamHook {
    private val logger = LoggerWithTag("RefreshStreamHook")

    @JvmStatic
    fun tryPushRefreshRequest(presenter: TheatreViewCoordinatorPresenter?) {
        logger.debug("current presenter: $presenter")
        if (presenter == null) {
            logger.error("presenter is null!")
            return
        }
        val provider = presenter.getPrivateField<DataProvider<TheatreCoordinatorRequest>>(
            fieldName = "theatreCoordinatorRequestProvider"
        ) as SharedEventDispatcher
        logger.debug("provider: $provider")
        Handler(Looper.getMainLooper()).post {
            logger.debug("Pushing RetryVideoPlay")
            provider.pushUpdate(TheatreCoordinatorRequest.RetryVideoPlay.INSTANCE)
        }
    }

    @JvmStatic
    fun getRefreshStreamButton(view: View): ImageView {
        return ViewFactory.createRefreshStreamButton(view = view).apply {
            changeVisibility(Flag.SHOW_REFRESH_BUTTON.asBoolean())
        }
    }

    @JvmStatic
    fun bind(
        rxBottomPlayerControlOverlayViewDelegate: IRxBottomPlayerControlOverlayViewDelegate,
        refreshStreamButton: ImageView?
    ) {
        refreshStreamButton?.setOnClickListener {
            Handler(Looper.getMainLooper()).post {
                logger.debug("Calling pushRefreshEvent() from MainLooper")
                rxBottomPlayerControlOverlayViewDelegate.pushRefreshEvent()
            }
        }
    }

    @JvmStatic
    fun tryPushRefreshRequest(du: DataUpdater<TheatreCoordinatorRequest>?) {
        logger.debug("du: $du")
        if (du == null) {
            return
        }
        Handler(Looper.getMainLooper()).post {
            logger.debug("Pushing RetryVideoPlay")
            du.pushUpdate(TheatreCoordinatorRequest.RetryVideoPlay.INSTANCE)
        }
    }
}