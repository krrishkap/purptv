package tv.purple.monolith.component.blacklist.data.repository

import io.reactivex.Flowable
import tv.purple.monolith.component.blacklist.data.model.KeywordData
import tv.purple.monolith.component.blacklist.data.source.BlacklistSource
import tv.purple.monolith.core.db.Entity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlacklistRepository @Inject constructor(
    private val source: BlacklistSource,
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

    fun addNewItems(rawText: String) {
        source.addRawText(rawText)
    }

    fun update(item: Entity<KeywordData>) {
        source.update(item)
    }


    fun changeType(item: Entity<KeywordData>, newType: KeywordData.Type) {
        return source.changeType(item, newType)
    }
}