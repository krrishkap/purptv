package tv.purple.monolith.features.logs.component.data.provider

import tv.purple.monolith.core.LoggerImpl
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.features.logs.component.data.model.MessageInfo
import tv.twitch.android.shared.chat.pub.messages.data.MessageToken
import java.util.concurrent.ConcurrentLinkedQueue

object LocalLogsProvider {
    private val logs = LimitedQueue<MessageInfo>()

    fun addMessage(messageDesc: MessageInfo) {
        if (!logs.contains(messageDesc)) {
            if (!hasSameMessage(messageDesc, logs)) {
                logs.add(messageDesc)
            } else {
                LoggerImpl.debug("msg: $messageDesc")
            }
        }
    }

    private fun hasSameMessage(
        messageDesc: MessageInfo,
        logs: LimitedQueue<MessageInfo>
    ): Boolean {
        for (m in logs) {
            if (isSameMessage(messageDesc, m)) {
                return true
            }
        }

        return false
    }

    private fun isSameMessage(new: MessageInfo, old: MessageInfo): Boolean {
        if (new.userId != old.userId) {
            return false
        }

        if (new.channelId != old.channelId) {
            return false
        }

        if (new.timestamp > (old.timestamp + 5) || new.timestamp < (old.timestamp - 5)) {
            return false
        }

        if (new.msg.tokens.size != old.msg.tokens.size) {
            return false
        }

        if (!isSameTokens(new.msg.tokens, old.msg.tokens)) {
            return false
        }

        return true
    }

    private fun isSameTokens(
        tokens: MutableList<MessageToken>,
        tokens2: MutableList<MessageToken>
    ): Boolean {
        if (tokens.size != tokens2.size) {
            return false
        }

        tokens.zip(tokens2).forEach { pair ->
            if (pair.first.javaClass != pair.second.javaClass) {
                return false
            }

            val newToken = pair.first
            val oldToken = pair.second

            when (newToken) {
                is MessageToken.TextToken -> {
                    if (oldToken is MessageToken.TextToken) {
                        if (newToken.text != oldToken.text) {
                            return false
                        }
                    } else {
                        return false
                    }
                }

                is MessageToken.EmoticonToken -> {
                    if (oldToken is MessageToken.EmoticonToken) {
                        if (newToken.text != oldToken.text) {
                            return false
                        }
                    } else {
                        return false
                    }
                }

                is MessageToken.MentionToken -> {
                    if (oldToken is MessageToken.MentionToken) {
                        if (newToken.userName != oldToken.userName) {
                            return false
                        }
                    } else {
                        return false
                    }
                }

                is MessageToken.BitsToken -> {
                    if (oldToken is MessageToken.BitsToken) {
                        if (newToken.numBits != oldToken.numBits) {
                            return false
                        }
                        if (newToken.prefix != oldToken.prefix) {
                            return false
                        }
                    } else {
                        return false
                    }
                }

                is MessageToken.UrlToken -> {
                    if (oldToken is MessageToken.UrlToken) {
                        if (newToken.hidden != oldToken.hidden) {
                            return false
                        }
                        if (newToken.url != oldToken.url) {
                            return false
                        }
                    } else {
                        return false
                    }
                }
            }
        }

        return true
    }

    fun getMessages(channelId: Int, userId: Int): List<MessageInfo> =
        logs.filter { it.userId == userId && it.channelId == channelId }
            .sortedBy { it.timestamp }
            .toList()

    class LimitedQueue<E> : ConcurrentLinkedQueue<E>() {
        override fun offer(e: E): Boolean {
            val offer = super.offer(e)
//            while (offer && size > Flag.LOCAL_LOGS.asString().toInt()) {
//                super.poll()
//            }
            return offer
        }
    }
}