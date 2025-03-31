package tv.purple.monolith.features.settings.bridge

import tv.twitch.android.shared.settings.BaseSettingsPresenter

interface IModFragment {
    fun setPresenter(presenter: BaseSettingsPresenter)
}