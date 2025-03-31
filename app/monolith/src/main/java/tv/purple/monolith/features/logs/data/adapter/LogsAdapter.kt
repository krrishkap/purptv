package tv.purple.monolith.features.logs.data.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tv.purple.monolith.core.util.ViewUtil
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.inflate
import tv.purple.monolith.features.chat.ChatHookProvider
import tv.purple.monolith.features.logs.component.data.model.MessageItem
import tv.twitch.android.shared.chat.messages.ui.ChatMessageFactoryV2
import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage.LiveChatMessage
import tv.twitch.android.shared.ui.elements.GlideHelper
import java.text.DateFormat
import java.util.Date
import javax.inject.Inject

class LogsAdapter @Inject constructor(
    val chatHookProvider: ChatHookProvider
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var messages: List<MessageItem> = listOf()
    lateinit var chatMessageFactoryV2: ChatMessageFactoryV2
    var channelId: Int = 0

    fun bindFactory(factory: ChatMessageFactoryV2, id: Int) {
        chatMessageFactoryV2 = factory
        channelId = id
    }

    override fun getItemViewType(position: Int): Int {
        val res = messages[position]

        return if (res is MessageItem.Header) {
            HEADER_TYPE
        } else {
            CONTENT_TYPE
        }
    }

    class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tv = view.getView<TextView>(resName = "purpletv_logs_date__tv")

        fun onBind(date: Date) {
            tv.text = DateFormat.getDateInstance(DateFormat.SHORT).format(date)
        }
    }

    inner class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tv = view.getView<TextView>(resName = "chat_message_item")
        private val clip1 = view.getView<View>(resName = "inline_clip_preview")
        private val clip2 = view.getView<View>(resName = "inline_clip_preview_placeholder")
        private val clip3 = view.getView<View>(resName = "inline_clip_error")

        fun onBind(factory: ChatMessageFactoryV2, message: LiveChatMessage, channelId: Int) {
            ViewUtil.hide(clip1, clip2, clip3)
            val spanned = factory.createChatHistoryMessage(
                itemView.context,
                chatHookProvider.hookLiveChatMessage(message, channelId)
            )
            tv.setText(
                spanned, TextView.BufferType.SPANNABLE
            )
            GlideHelper.loadImagesFromSpanned(itemView.context, spanned, tv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_TYPE -> DateViewHolder(
                parent.inflate(
                    resName = "purpletv_logs_date",
                    parent = parent
                )
            )

            else -> MessageViewHolder(
                parent.inflate(
                    resName = "chat_message_item",
                    parent = parent
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        if (message is MessageItem.Content) {
            (holder as MessageViewHolder).onBind(
                chatMessageFactoryV2,
                message.content,
                channelId
            )
        } else if (message is MessageItem.Header) {
            (holder as DateViewHolder).onBind(message.date)
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(it: List<MessageItem>) {
        messages = it
        notifyDataSetChanged()
    }

    companion object {
        private const val CONTENT_TYPE = 0
        private const val HEADER_TYPE = 1
    }
}
