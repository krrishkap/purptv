package tv.purple.monolith.models.retrofit.chatsen

data class BadgesResponse(
    val badges: List<BadgeModel>?,
    val users: List<UserModel>?
)


data class UserModel(
    val id: String?,
    val badges: List<BadgeRefModel>?
)

data class BadgeRefModel(
    val badgeName: String?
)

data class BadgeModel(
    val name: String?,
    val image: String?
)