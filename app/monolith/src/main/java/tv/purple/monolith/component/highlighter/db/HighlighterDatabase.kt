package tv.purple.monolith.component.highlighter.db

import android.content.Context
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.subjects.BehaviorSubject
import tv.purple.monolith.component.highlighter.data.model.KeywordData
import tv.purple.monolith.core.db.Entity
import tv.purple.monolith.core.db.TinyDB
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HighlighterDatabase @Inject constructor(
    context: Context,
    db_name: String
) : TinyDB.Callback {
    private val subject = BehaviorSubject.create<List<Entity<KeywordData>>>()

    private val db = TinyDB<KeywordData>(context, db_name, this)

    init {
        invalidate()
    }

    fun getFlow(): Flowable<List<Entity<KeywordData>>> {
        return subject.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun get(id: String): Maybe<Entity<KeywordData>> {
        val data = db.getObject(id, KeywordData::class.java) ?: return Maybe.empty()

        return Maybe.just(data)
    }

    fun insert(data: KeywordData) {
        db.putObject(data)
    }

    fun insert(keywords: List<KeywordData>) {
        db.putObjects(keywords)
    }

    fun remove(entity: Entity<KeywordData>) {
        db.removeObject(entity)
    }

    fun update(entity: Entity<KeywordData>) {
        db.updateObject(entity)
    }

    override fun invalidate() {
        subject.onNext(db.getAll(KeywordData::class.java))
    }
}