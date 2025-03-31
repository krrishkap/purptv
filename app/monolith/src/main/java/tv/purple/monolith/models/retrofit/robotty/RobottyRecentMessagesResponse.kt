package tv.purple.monolith.models.retrofit.robotty

data class RobottyRecentMessagesResponse(
    val messages: List<String>,
    val error: String,
    val error_code: String
)
