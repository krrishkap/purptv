package tv.purple.monolith.models.retrofit.nop

data class HomiesBadges(
    val badges: List<HomiesBadgeModel>,
    val baseUrl: String,
    val total: Int,
    val cached: Int
)
