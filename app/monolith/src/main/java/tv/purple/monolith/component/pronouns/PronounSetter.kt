package tv.purple.monolith.component.pronouns

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import android.widget.TextView
import tv.purple.monolith.component.pronouns.bridge.PaddingBackgroundSpan
import tv.twitch.android.shared.chat.messages.ui.ChatMessageViewHolder
import java.lang.ref.WeakReference


class PronounSetter(view: ChatMessageViewHolder) {
    private val ref = WeakReference(view)

    fun setText(pronounText: String) {
        val text = ref.get()?.messageTextView?.text
        if (text.isNullOrBlank()) {
            return
        }

        val spanned = text as Spanned

        val ssb = SpannableStringBuilder(pronounText).apply {
            setSpan(
                PaddingBackgroundSpan(Color.RED, Color.WHITE, 12),
                0,
                length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        ref.get()?.messageTextView?.setText(
            SpannedString(ssb.append(" ").append(spanned)),
            TextView.BufferType.SPANNABLE
        )
        destroy()
    }

    fun destroy() {
        ref.clear()
    }
}