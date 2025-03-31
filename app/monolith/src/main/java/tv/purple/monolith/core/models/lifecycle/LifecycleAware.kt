package tv.purple.monolith.core.models.lifecycle

import tv.twitch.android.core.user.TwitchAccountManager

interface LifecycleAware {
    fun onAllComponentDestroyed()
    fun onAllComponentStopped()
    fun onAccountLogout()
    fun onFirstActivityCreated()
    fun onFirstActivityStarted()
    fun onConnectedToChannel(channelId: Int)
    fun onConnectingToChannel(channelId: Int)
    fun onAccountLogin(tam: TwitchAccountManager)
}