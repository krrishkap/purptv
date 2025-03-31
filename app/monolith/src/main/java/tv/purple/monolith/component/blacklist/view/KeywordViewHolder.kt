package tv.purple.monolith.component.blacklist.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tv.purple.monolith.component.blacklist.data.model.KeywordData
import tv.purple.monolith.core.ResManager.fromResToDrawableId
import tv.purple.monolith.core.db.Entity
import tv.purple.monolith.core.util.ViewUtil.changeVisibility
import tv.purple.monolith.core.util.ViewUtil.getView

class KeywordViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val keyword = view.getView<TextView>("purpletv_highlighter_item__keyword")
    private val picker = view.getView<ImageView>("purpletv_highlighter_item__picker")
    private val type = view.getView<ImageView>("purpletv_highlighter_item__type")
    private val vibration = view.getView<ImageView>("purpletv_highlighter_item__vibration")

    fun bind(entity: Entity<KeywordData>, listener: ClickListener) {
        val item = entity.getData()
        keyword.text = item.word
        renderType(item.type)
        type.setOnClickListener {
            listener.onChangeTypeClicked(entity)
        }
        picker.changeVisibility(false)
        vibration.changeVisibility(false)
    }

    private fun renderType(keywordType: KeywordData.Type) {
        val resId = when (keywordType) {
            KeywordData.Type.INSENSITIVE -> "ic_purpletv_case_insensitive"
            KeywordData.Type.CASESENSITIVE -> "ic_purpletv_case_sensitive"
            KeywordData.Type.USERNAME -> "ic_purpletv_user"
        }.fromResToDrawableId()

        type.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                resId
            )
        )
    }
}