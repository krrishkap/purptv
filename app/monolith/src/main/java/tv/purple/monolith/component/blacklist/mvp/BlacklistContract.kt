package tv.purple.monolith.component.blacklist.mvp

import tv.purple.monolith.component.blacklist.data.model.KeywordData
import tv.purple.monolith.core.db.Entity

interface BlacklistContract {
    interface View {
        fun render(state: State)
        fun showAddKeywordsDialog()

        sealed class State {
            object Loading : State()
            data class Update(val keywords: List<Entity<KeywordData>>) : State()
        }
    }

    abstract class Presenter(val view: View) {
        abstract fun onStart()
        abstract fun onStop()

        abstract fun onAddButtonClicked()

        abstract fun onChangeTypeItemClicked(keyword: Entity<KeywordData>)

        abstract fun onItemRemoved(keyword: Entity<KeywordData>)

        abstract fun addNewItems(rawText: String)
        abstract fun onChangeTypeClicked(item: Entity<KeywordData>)
    }
}