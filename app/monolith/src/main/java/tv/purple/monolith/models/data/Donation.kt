package tv.purple.monolith.models.data

data class Donation(
    val userId: Int,
    val userName: String,
    val displayName: String,
    val badgeUrl: String? = null
)
