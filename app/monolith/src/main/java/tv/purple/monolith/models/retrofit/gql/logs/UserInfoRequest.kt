package tv.purple.monolith.models.retrofit.gql.logs

data class UserInfoRequest(
    val username: String
) {
    val query: String =
        "query UserInfo(\$userLogin: String) {\n" +
                "    user(login: \$userLogin) {\n" +
                "        id\n" +
                "        login\n" +
                "    }\n" +
                "}"
    val variables: Map<String, Any> = mapOf("userLogin" to username)
}
