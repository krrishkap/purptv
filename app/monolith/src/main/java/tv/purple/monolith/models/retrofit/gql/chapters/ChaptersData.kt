package tv.purple.monolith.models.retrofit.gql.chapters

data class ChaptersData(
    val video: VideoData
)

data class VideoData(
    val moments: MomentData,
    val game: GameData
)

data class MomentData(
    val edges: List<EdgeData>
)

data class EdgeData(
    val node: NodeData
)

data class NodeData(
    val durationMilliseconds: Int,
    val positionMilliseconds: Int,
    val description: String,
    val details: DetailsData
)

data class DetailsData(
    val game: GameData
)

data class GameData(
    val boxArtURL: String,
    val displayName: String
)