package tv.purple.monolith.component.highlighter.view

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.component.highlighter.data.model.KeywordData
import tv.purple.monolith.core.ResManager.fromResToDrawableId
import tv.purple.monolith.core.db.Entity
import tv.purple.monolith.core.util.ViewUtil.getView

class KeywordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val keyword = view.getView<TextView>(RES_STRINGS.purpletv_highlighter_item__keyword)
    private val picker = view.getView<ImageView>(RES_STRINGS.purpletv_highlighter_item__picker)
    private val type = view.getView<ImageView>(RES_STRINGS.purpletv_highlighter_item__type)
    private val vibration = view.getView<ImageView>(RES_STRINGS.purpletv_highlighter_item__vibration)

    fun bind(item: Entity<KeywordData>, listener: ClickListener) {
        keyword.text = item.getData().word
        picker.setOnClickListener {
            listener.onChangeColorClicked(item)
        }
        picker.setImageDrawable(ColorDrawable(item.getData().color))
        renderType(item.getData().type)
        vibration.setOnClickListener {
            listener.onVibrationClicked(item)
        }
        type.setOnClickListener {
            listener.onChangeTypeClicked(item)
        }
        renderVibration(item.getData().vibration)
    }

    private fun renderVibration(vibration: Boolean) {
        val resId = if (vibration) {
            RES_STRINGS.ic_purpletv_vibration
        } else {
            RES_STRINGS.ic_purpletv_no_vibration
        }.fromResToDrawableId()

        this.vibration.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                resId
            )
        )
    }

    private fun renderType(keywordType: KeywordData.Type) {
        val resId = when (keywordType) {
            KeywordData.Type.INSENSITIVE -> RES_STRINGS.ic_purpletv_case_insensitive
            KeywordData.Type.CASESENSITIVE -> RES_STRINGS.ic_purpletv_case_sensitive
            KeywordData.Type.USERNAME -> RES_STRINGS.ic_purpletv_user
        }.fromResToDrawableId()

        type.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                resId
            )
        )
    }
}