package tv.purple.monolith.component.highlighter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tv.purple.monolith.component.highlighter.data.model.HighlightDescriptionItem
import tv.purple.monolith.component.highlighter.data.model.KeywordData
import tv.purple.monolith.component.highlighter.data.repository.HighlighterRepository
import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage
import tv.twitch.android.shared.chat.pub.messages.data.MessageTokenV2
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HighlighterDelegate @Inject constructor(
    private val repository: HighlighterRepository
) {
    private var usernames: Map<String, HighlightDescriptionItem> = emptyMap()
    private var sensitive: Map<String, HighlightDescriptionItem> = emptyMap()
    private var insensitive: Map<String, HighlightDescriptionItem> = emptyMap()

    private val disposables = CompositeDisposable()

    var isEnabled: Boolean = false

    fun pull() {
        disposables.add(
            repository.getFlow().observeOn(Schedulers.single()).subscribe({ keywords ->
                val usernames = LinkedHashMap<String, HighlightDescriptionItem>()
                val sensitive = LinkedHashMap<String, HighlightDescriptionItem>()
                val insensitive = LinkedHashMap<String, HighlightDescriptionItem>()
                keywords?.map { it.getData() }?.forEach {
                    when (it.type) {
                        KeywordData.Type.INSENSITIVE -> {
                            insensitive[it.word.lowercase()] = HighlightDescriptionItem(it.color, it.vibration)
                        }

                        KeywordData.Type.CASESENSITIVE -> {
                            sensitive[it.word] = HighlightDescriptionItem(it.color, it.vibration)
                        }

                        KeywordData.Type.USERNAME -> {
                            usernames[it.word.lowercase()] = HighlightDescriptionItem(it.color, it.vibration)
                        }
                    }
                }

                this@HighlighterDelegate.usernames = usernames
                this@HighlighterDelegate.insensitive = insensitive
                this@HighlighterDelegate.sensitive = sensitive

                isEnabled = usernames.isNotEmpty() || sensitive.isNotEmpty() || insensitive.isNotEmpty()
            }, {
                it.printStackTrace()
            })
        )
    }


    fun getHighlightDesc(liveChatMessage: ChatMessage.LiveChatMessage): HighlightDescriptionItem? {
        if (!isEnabled) {
            return null
        }

        usernames[liveChatMessage.sender.username.lowercase()]?.let { color ->
            return color
        }

        liveChatMessage.messageTokens.forEach { token ->
            if (token is MessageTokenV2.TextToken) {
                token.text.split("\\s+".toRegex()).map { it.trim(' ') }
                    .forEach { word ->
                        if (word.isNotBlank()) {
                            sensitive[word]?.let { color ->
                                return color
                            }
                            insensitive[word.lowercase()]?.let { color ->
                                return color
                            }
                        }
                    }
            }
        }

        return null
    }
}