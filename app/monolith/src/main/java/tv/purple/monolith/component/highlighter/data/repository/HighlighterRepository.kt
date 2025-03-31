package tv.purple.monolith.component.highlighter.data.repository

import io.reactivex.Flowable
import tv.purple.monolith.component.highlighter.data.model.KeywordData
import tv.purple.monolith.component.highlighter.data.source.HighlighterSource
import tv.purple.monolith.core.db.Entity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HighlighterRepository @Inject constructor(
    private val source: HighlighterSource,
) {
    fun getFlow(): Flowable<List<Entity<KeywordData>>> {
        return source.getFlow()
    }

    fun delete(keyword: Entity<KeywordData>) {
        source.delete(keyword)
    }

    fun add(keyword: KeywordData) {
        source.add(keyword)
    }

    fun changeType(item: Entity<KeywordData>, newType: KeywordData.Type) {
        source.changeType(item, newType)
    }

    fun addNewItems(rawText: String) {
        source.addRawText(rawText)
    }

    fun update(item: Entity<KeywordData>) {
        source.update(item)
    }
}