package tv.purple.monolith.bridge.ex

import dagger.internal.Provider
import tv.twitch.android.core.data.source.DataUpdater
import tv.twitch.android.shared.theatre.data.pub.model.TheatreCoordinatorRequest

interface IRxBottomPlayerControlOverlayPresenter {
    fun inject(provider: Provider<DataUpdater<TheatreCoordinatorRequest>>)
}