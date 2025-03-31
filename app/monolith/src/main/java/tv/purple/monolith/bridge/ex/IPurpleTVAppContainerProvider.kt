package tv.purple.monolith.bridge.ex

import tv.purple.monolith.models.IPurpleTVAppContainer

interface IPurpleTVAppContainerProvider {
    fun providePurpleTVAppContainer(): IPurpleTVAppContainer
}