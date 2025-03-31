package tv.purple.monolith.component.highlighter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.component.cp.ColorPickerDialog
import tv.purple.monolith.component.highlighter.data.model.HighlightDescriptionItem
import tv.purple.monolith.component.highlighter.view.HighlighterFragment
import tv.purple.monolith.core.PrefManager
import tv.purple.monolith.core.models.flag.Flag
import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class Highlighter @Inject constructor(
    private val highlighterDelegate: HighlighterDelegate,
    private val provider: Provider<HighlighterFragment>
) {
    fun getHighlightDesc(liveChatMessage: ChatMessage.LiveChatMessage): HighlightDescriptionItem? {
        return highlighterDelegate.getHighlightDesc(liveChatMessage)
    }

    fun createHighlighterFragment(): Fragment {
        return provider.get()
    }

    fun isEnabled(): Boolean {
        return highlighterDelegate.isEnabled
    }

    fun pull() {
        highlighterDelegate.pull()
    }

    fun showChangeMentionHighlightColorDialog(activity: FragmentActivity) {
        ColorPickerDialog(Flag.USER_MENTION_COLOR.asInt()) { color ->
            PrefManager.setUserMentionColor(color)
        }.show(activity.supportFragmentManager, RES_STRINGS.purpletv_change_color_tag)
    }
}