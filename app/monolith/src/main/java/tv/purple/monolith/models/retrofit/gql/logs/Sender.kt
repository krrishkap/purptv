package tv.purple.monolith.models.retrofit.gql.logs

data class Sender(
    val id: String?,
    val login: String?,
    val chatColor: String?,
    val displayName: String?,
    val displayBadges: List<DisplayBadge>?,
    val __typename: String
)
