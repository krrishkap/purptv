package tv.purple.monolith.core.models.lifecycle

interface LifecycleController {
    fun register(vararg listeners: LifecycleAware)
    fun unregister(vararg listeners: LifecycleAware)
}