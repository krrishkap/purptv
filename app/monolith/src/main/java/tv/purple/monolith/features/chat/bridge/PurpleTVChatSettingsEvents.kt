package tv.purple.monolith.features.chat.bridge

import tv.twitch.android.shared.chat.settings.entry.ChatSettingsViewDelegate

sealed class PurpleTVChatSettingsEvents : ChatSettingsViewDelegate.ChatSettingsEvents(null)
class Closable : PurpleTVChatSettingsEvents()
class Toggle : PurpleTVChatSettingsEvents()