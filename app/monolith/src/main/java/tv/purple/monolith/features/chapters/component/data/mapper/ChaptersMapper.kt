package tv.purple.monolith.features.chapters.component.data.mapper

import tv.purple.monolith.core.util.DateUtil
import tv.purple.monolith.features.chapters.component.data.model.Chapter
import tv.purple.monolith.models.retrofit.gql.chapters.ChaptersData
import tv.purple.monolith.models.retrofit.gql.logs.DataResponse
import javax.inject.Inject

class ChaptersMapper @Inject constructor() {
    fun map(response: DataResponse<ChaptersData>): List<Chapter> {
        return response.data?.video?.moments?.edges?.map { edge ->
            edge.node.let { node ->
                Chapter(
                    title = node.description,
                    timestamp = DateUtil.toSeconds(node.positionMilliseconds.toLong()).toInt(),
                    url = node.details.game.boxArtURL
                )
            }
        }.takeIf { !it.isNullOrEmpty() } ?: listOf(
            Chapter(
                title = response.data?.video?.game?.displayName ?: "<DEBUG>",
                timestamp = 0,
                url = response.data?.video?.game?.boxArtURL
            )
        )
    }
}