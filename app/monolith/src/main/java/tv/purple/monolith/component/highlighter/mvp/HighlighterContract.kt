package tv.purple.monolith.component.highlighter.mvp

import tv.purple.monolith.core.db.Entity
import tv.purple.monolith.component.highlighter.data.model.KeywordData

interface HighlighterContract {
    interface View {
        fun render(state: State)
        fun showAddKeywordsDialog()
        fun showChangeItemColorDialog(keyword: Entity<KeywordData>)

        sealed class State {
            object Loading : State()
            data class Update(val keywords: List<Entity<KeywordData>>) : State()
        }
    }

    abstract class Presenter {
        abstract fun onStart()
        abstract fun onStop()
        abstract fun attachView(view: View)
        abstract fun detachView()

        abstract fun onAddButtonClicked()

        abstract fun onChangeTypeItemClicked(keyword: Entity<KeywordData>)
        abstract fun onChangeColorItemClicked(keyword: Entity<KeywordData>)
        abstract fun onVibrationItemClicked(keyword: Entity<KeywordData>)

        abstract fun onItemRemoved(keyword: Entity<KeywordData>)
        abstract fun onItemColorChanged(keyword: Entity<KeywordData>, newColor: Int)

        abstract fun addNewItems(rawText: String)
    }
}