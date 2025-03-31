package tv.purple.monolith.component.blacklist.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import tv.purple.monolith.component.blacklist.data.model.KeywordData
import tv.purple.monolith.component.blacklist.data.repository.BlacklistRepository
import tv.purple.monolith.component.blacklist.mvp.BlacklistContract
import tv.purple.monolith.core.db.Entity

class BlacklistPresenter(
    view: BlacklistContract.View
) : BlacklistContract.Presenter(view) {
    private val disposables = CompositeDisposable()

    private lateinit var repository: BlacklistRepository

    override fun onStart() {
        fill()
    }

    fun injectRepository(repo: BlacklistRepository) {
        repository = repo
    }

    private fun fill() {
        view.render(BlacklistContract.View.State.Loading)
        disposables.add(repository.getFlow().observeOn(AndroidSchedulers.mainThread()).subscribe({
            view.render(BlacklistContract.View.State.Update(it))
        }, { it.printStackTrace() }))
    }

    override fun onStop() {
        disposables.clear()
    }

    override fun onItemRemoved(keyword: Entity<KeywordData>) {
        repository.delete(keyword = keyword)
    }

    override fun addNewItems(rawText: String) {
        repository.addNewItems(rawText)
    }

    override fun onChangeTypeClicked(item: Entity<KeywordData>) {
        repository.changeType(
            item = item,
            newType = when (item.getData().type) {
                KeywordData.Type.CASESENSITIVE -> KeywordData.Type.INSENSITIVE
                KeywordData.Type.INSENSITIVE -> KeywordData.Type.USERNAME
                KeywordData.Type.USERNAME -> KeywordData.Type.CASESENSITIVE
            }
        )
    }

    override fun onChangeTypeItemClicked(keyword: Entity<KeywordData>) {
        repository.changeType(
            item = keyword,
            newType = when (keyword.getData().type) {
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