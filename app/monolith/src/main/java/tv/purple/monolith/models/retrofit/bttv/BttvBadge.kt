package tv.purple.monolith.models.retrofit.bttv

data class BttvBadge(
    val providerId: String,
    val badge: BadgeData
)

data class BadgeData(
    val svg: String,
    val description: String
)
