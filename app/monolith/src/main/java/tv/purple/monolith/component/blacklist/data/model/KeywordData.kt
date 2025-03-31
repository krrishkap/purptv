package tv.purple.monolith.component.blacklist.data.model

data class KeywordData(
    val word: String,
    val type: Type
) {
    enum class Type {
        CASESENSITIVE,
        INSENSITIVE,
        USERNAME
    }
}
