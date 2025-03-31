package tv.purple.monolith.bridge.ex

import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateEvent

interface IProxyEvent {
    fun proxyEvent(event: ViewDelegateEvent)
}