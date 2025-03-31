package tv.purple.monolith.core

import tv.purple.monolith.bridge.PurpleTVAppContainer
import tv.purple.monolith.core.models.lifecycle.LifecycleAware
import tv.purple.monolith.core.models.lifecycle.LifecycleController
import tv.twitch.android.core.user.TwitchAccountManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LifecycleCore @Inject constructor() : LifecycleController, LifecycleAware {
    private val lifecycleModules = mutableSetOf<LifecycleAware>()

    override fun register(vararg listeners: LifecycleAware) {
        listeners.forEach { listener ->
            lifecycleModules.add(listener)
        }
    }

    override fun unregister(vararg listeners: LifecycleAware) {
        listeners.forEach { listener ->
            lifecycleModules.remove(listener)
        }
    }

    override fun onAllComponentDestroyed() {
        lifecycleModules.forEach {
            it.onAllComponentDestroyed()
        }
    }

    override fun onAllComponentStopped() {
        lifecycleModules.forEach {
            it.onAllComponentStopped()
        }
    }

    override fun onAccountLogout() {
        lifecycleModules.forEach {
            it.onAccountLogout()
        }
    }

    override fun onFirstActivityCreated() {
        lifecycleModules.forEach {
            it.onFirstActivityCreated()
        }
    }

    override fun onFirstActivityStarted() {
        lifecycleModules.forEach {
            it.onFirstActivityStarted()
        }
    }

    override fun onConnectedToChannel(channelId: Int) {
        lifecycleModules.forEach {
            it.onConnectedToChannel(channelId)
        }
    }

    override fun onConnectingToChannel(channelId: Int) {
        lifecycleModules.forEach {
            it.onConnectingToChannel(channelId)
        }
    }

    override fun onAccountLogin(tam: TwitchAccountManager) {
        lifecycleModules.forEach {
            it.onAccountLogin(tam)
        }
    }

    companion object {
        fun get(): LifecycleCore = PurpleTVAppContainer.getLifecycle()
    }
}