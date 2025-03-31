package tv.purple.monolith.features.chapters.component.data.repository

import io.reactivex.Single
import tv.purple.monolith.features.chapters.component.data.model.Chapter
import tv.purple.monolith.features.chapters.component.data.source.ChaptersDataSource
import javax.inject.Inject

class ChaptersRepository @Inject constructor(
    val source: ChaptersDataSource
) {
    fun getChapters(vodId: String): Single<List<Chapter>> {
        return source.getChapters(vodId = vodId)
    }
}