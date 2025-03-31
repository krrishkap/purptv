package tv.purple.monolith.features.chapters.component.data.source

import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.TwitchGQLApi
import tv.purple.monolith.features.chapters.component.data.mapper.ChaptersMapper
import tv.purple.monolith.features.chapters.component.data.model.Chapter
import tv.purple.monolith.models.retrofit.gql.chapters.ChaptersRequest
import javax.inject.Inject

class ChaptersDataSource @Inject constructor(
    val tcp: TwitchGQLApi,
    val mapper: ChaptersMapper
) {
    fun getChapters(vodId: String): Single<List<Chapter>> {
        return tcp.requestChapters(ChaptersRequest(videoId = vodId)).map(mapper::map)
    }
}