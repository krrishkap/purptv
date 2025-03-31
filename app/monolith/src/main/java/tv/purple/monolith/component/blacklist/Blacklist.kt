package tv.purple.monolith.component.blacklist

import androidx.fragment.app.Fragment
import tv.purple.monolith.component.blacklist.data.repository.BlacklistRepository
import tv.purple.monolith.component.blacklist.view.BlacklistFragment
import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage
import tv.twitch.chat.library.model.ChatLiveMessage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Blacklist @Inject constructor(
    private val highlighterDelegate: BlacklistDelegate,
    private val repository: BlacklistRepository
) {
    fun isBlacklisted(clm: ChatLiveMessage): Boolean {
        return highlighterDelegate.isBlacklisted(clm)
    }

    fun isBlacklisted(lcm: ChatMessage.LiveChatMessage): Boolean {
        return highlighterDelegate.isBlacklisted(lcm)
    }

    fun createBlacklistFragment(): Fragment {
        return BlacklistFragment.newInstance(repository)
    }

    fun isEnabled(): Boolean {
        return highlighterDelegate.isEnabled
    }

    fun dispose() {
        highlighterDelegate.dispose()
    }

    fun pull() {
        highlighterDelegate.pull()
    }
}