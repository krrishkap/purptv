package tv.purple.monolith.features.ui.misc

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tv.purple.monolith.bridge.DiffUtilCallback
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.component.blacklist.data.model.KeywordData
import tv.purple.monolith.component.blacklist.view.ClickListener
import tv.purple.monolith.component.blacklist.view.KeywordViewHolder
import tv.purple.monolith.core.db.Entity
import tv.purple.monolith.core.util.ViewUtil.inflate

class BlacklistAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<KeywordViewHolder>() {
    private val items = mutableListOf<Entity<KeywordData>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        val view = parent.inflate(RES_STRINGS.purpletv_highlighter_item, parent = parent)
        return KeywordViewHolder(view)
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun add(keyword: Entity<KeywordData>) {
        items.add(keyword)
        notifyDataSetChanged()
    }

    fun removeAt(adapterPosition: Int) {
        items.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    fun setData(keywords: List<Entity<KeywordData>>) {
        val callback = DiffUtilCallback(items, keywords)
        val result = DiffUtil.calculateDiff(callback)
        items.clear()
        items.addAll(keywords)
        result.dispatchUpdatesTo(AdapterListUpdateCallback(this)) // Direct call to result.dispatchUpdatesTo(this) cause crash
    }

    fun getItem(pos: Int): Entity<KeywordData>? {
        return if (pos < 0 || pos >= items.size) {
            null
        } else {
            return items[pos]
        }
    }
}