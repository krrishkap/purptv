package tv.purple.monolith.component.highlighter.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import tv.purple.monolith.component.highlighter.data.model.KeywordData
import tv.purple.monolith.component.highlighter.data.repository.HighlighterRepository
import tv.purple.monolith.component.highlighter.mvp.HighlighterContract
import tv.purple.monolith.core.db.Entity

class HighlighterPresenter(
    private val repository: HighlighterRepository
) : HighlighterContract.Presenter() {
    lateinit var view: HighlighterContract.View

    private val disposables = CompositeDisposable()

    override fun onStart() {
        view.render(HighlighterContract.View.State.Loading)
        disposables.add(repository.getFlow().observeOn(AndroidSchedulers.mainThread()).subscribe({
            view.render(HighlighterContract.View.State.Update(it))
        }, { it.printStackTrace() }))
    }

    override fun onStop() {
        disposables.clear()
    }

    override fun attachView(view: HighlighterContract.View) {
        this.view = view
    }

    override fun detachView() {
    }

    override fun onItemRemoved(keyword: Entity<KeywordData>) {
        repository.delete(keyword = keyword)
    }

    override fun onItemColorChanged(keyword: Entity<KeywordData>, newColor: Int) {
        repository.update(
            item = keyword.copy(keyword.getData().copy(color = newColor))
        )
    }

    override fun addNewItems(rawText: String) {
        repository.addNewItems(rawText)
    }

    override fun onVibrationItemClicked(keyword: Entity<KeywordData>) {
        val data = keyword.getData()
        repository.update(
            item = keyword.copy(data.copy(vibration = !data.vibration))
        )
    }

    override fun onChangeColorItemClicked(keyword: Entity<KeywordData>) {
        view.showChangeItemColorDialog(keyword)
    }

    override fun onChangeTypeItemClicked(keyword: Entity<KeywordData>) {
        val data = keyword.getData()
        repository.changeType(
            item = keyword,
            newType = when (data.type) {
                KeywordData.Type.CASESENSITIVE -> KeywordData.Type.INSENSITIVE
                KeywordData.Type.INSENSITIVE -> KeywordData.Type.USERNAME
                KeywordData.Type.USERNAME -> KeywordData.Type.CASESENSITIVE
            }
        )
    }

    override fun onAddButtonClicked() {
        view.showAddKeywordsDialog()
    }
}