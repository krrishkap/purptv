package tv.purple.monolith.component.blacklist.data.source

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import tv.purple.monolith.component.blacklist.data.mapper.BlacklistMapper
import tv.purple.monolith.component.blacklist.data.model.KeywordData
import tv.purple.monolith.component.blacklist.db.BlacklistDatabase
import tv.purple.monolith.core.db.Entity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlacklistSource @Inject constructor(
    private val mapper: BlacklistMapper,
    private val db: BlacklistDatabase
) {
    fun getFlow(): Flowable<List<Entity<KeywordData>>> {
        return db.getFlow().subscribeOn(Schedulers.io())
    }

    fun delete(keyword: Entity<KeywordData>) {
        db.remove(keyword)
    }

    fun add(keyword: KeywordData) {
        db.insert(
            listOf(
                keyword
            )
        )
    }

    fun addRawText(rawText: String) {
        mapper.map(
            rawText = rawText
        ).map { keyword ->
            db.insert(keyword)
        }
    }

    fun update(item: Entity<KeywordData>) {
        db.update(item)
    }

    fun changeType(item: Entity<KeywordData>, newType: KeywordData.Type) {
        db.update(item.copy(item.getData().copy(type = newType)))
    }
}