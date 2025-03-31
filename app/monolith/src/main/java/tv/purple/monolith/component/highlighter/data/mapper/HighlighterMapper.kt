package tv.purple.monolith.component.highlighter.data.mapper

import tv.purple.monolith.component.highlighter.data.model.KeywordData
import javax.inject.Inject

class HighlighterMapper @Inject constructor() {
    fun map(rawText: String): List<KeywordData> {
        return rawText.toTokens().mapNotNull { word ->
            when (word[0]) {
                '@' -> {
                    if (word.length < 2) {
                        return@mapNotNull null
                    }
                    KeywordData(
                        word = word.substring(1).lowercase(),
                        type = KeywordData.Type.USERNAME,
                        color = KeywordData.DEFAULT_COLOR
                    )
                }

                '#' -> {
                    if (word.length < 2) {
                        return@mapNotNull null
                    }
                    KeywordData(
                        word = word.substring(1).lowercase(),
                        type = KeywordData.Type.INSENSITIVE,
                        color = KeywordData.DEFAULT_COLOR
                    )
                }

                else -> {
                    KeywordData(
                        word = word,
                        type = KeywordData.Type.CASESENSITIVE,
                        color = KeywordData.DEFAULT_COLOR
                    )
                }
            }
        }
    }

    companion object {
        fun String.toTokens(): List<String> = split("\\s+".toRegex()).filter {
            it.isNotBlank()
        }.map { it.trim() }
    }
}