package tv.purple.monolith.component.blacklist

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tv.purple.monolith.component.blacklist.data.model.KeywordData
import tv.purple.monolith.component.blacklist.data.source.BlacklistSource
import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage
import tv.twitch.android.shared.chat.pub.messages.data.MessageTokenV2
import tv.twitch.chat.library.model.ChatLiveMessage
import tv.twitch.chat.library.model.MessageToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlacklistDelegate @Inject constructor(
    val source: BlacklistSource
) {
    private val disposables = CompositeDisposable()

    var usernames = HashSet<String>()
    var sensitive = HashSet<String>()
    var insensitive = HashSet<String>()

    var isEnabled: Boolean = false

    fun pull() {
        disposables.add(
            source.getFlow().observeOn(Schedulers.single())
                .subscribe({ keywords ->
                    val usernames = HashSet<String>()
                    val sensitive = HashSet<String>()
                    val insensitive = HashSet<String>()
                    keywords.map { it.getData() }.forEach {
                        when (it.type) {
                            KeywordData.Type.INSENSITIVE -> {
                                insensitive.add(it.word.lowercase())
                            }

                            KeywordData.Type.CASESENSITIVE -> {
                                sensitive.add(it.word)
                            }

                            KeywordData.Type.USERNAME -> {
                                usernames.add(it.word.lowercase())
                            }
                        }
                    }

                    this@BlacklistDelegate.usernames = usernames
                    this@BlacklistDelegate.insensitive = insensitive
                    this@BlacklistDelegate.sensitive = sensitive

                    isEnabled = usernames.isNotEmpty() ||
                            sensitive.isNotEmpty() ||
                            insensitive.isNotEmpty()
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun isBlacklisted(lcm: ChatMessage.LiveChatMessage): Boolean {
        if (!isEnabled) {
            return false
        }

        if (usernames.contains(lcm.sender.username)) {
            return true
        }

        lcm.messageTokens.forEach { token ->
            when (token) {
                is MessageTokenV2.TextToken -> {
                    if (token.text.split(SPLIT_PATTERN)
                            .map { it.trim(' ') }
                            .any { isBlacklisted(it) }
                    ) {
                        return true
                    }
                }

                is MessageTokenV2.EmoteToken -> {
                    if (isBlacklisted(token.text)) {
                        return true
                    }
                }

                is MessageTokenV2.UrlToken -> {
                    if (isBlacklisted(token.url)) {
                        return true
                    }
                }
            }
        }

        return false
    }

    fun isBlacklisted(clm: ChatLiveMessage): Boolean {
        if (!isEnabled) {
            return false
        }

        val cmi = clm.messageInfo

        if (usernames.contains(cmi.userInfo.userName.lowercase())) {
            return true
        }

        cmi.tokens.forEach { token ->
            when (token) {
                is MessageToken.TextToken -> {
                    if (token.text.split(SPLIT_PATTERN)
                            .map { it.trim(' ') }
                            .any { isBlacklisted(it) }
                    ) {
                        return true
                    }
                }

                is MessageToken.EmoteToken -> {
                    if (isBlacklisted(token.text)) {
                        return true
                    }
                }

                is MessageToken.UrlToken -> {
                    if (isBlacklisted(token.url)) {
                        return true
                    }
                }
            }
        }

        return false
    }

    private fun isBlacklisted(word: String?): Boolean {
        word ?: return false

        if (word.isNotBlank()) {
            if (sensitive.contains(word)) {
                return true
            }
            if (insensitive.contains(word.lowercase())) {
                return true
            }
        }

        return false
    }

    fun dispose() {
        disposables.clear()
    }

    companion object {
        private val SPLIT_PATTERN = "[\\s,.!?-]".toRegex()
    }
}