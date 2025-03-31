package tv.purple.monolith.component.blacklist.data.mapper

import tv.purple.monolith.component.blacklist.data.model.KeywordData
import javax.inject.Inject

class BlacklistMapper @Inject constructor() {
    fun map(rawText: String): List<KeywordData> {
        return rawText.split("\\s+".toRegex())
            .filter { it.isNotBlank() }.map { it.trim() }.mapNotNull { word ->
                when (word[0]) {
                    '@' -> {
                        if (word.length < 2) {
                            return@mapNotNull null
                        }
                        KeywordData(
                            word = word.substring(1).lowercase(),
                            type = KeywordData.Type.USERNAME
                        )
                    }

                    '#' -> {
                        if (word.length < 2) {
                            return@mapNotNull null
                        }
                        KeywordData(
                            word = word.substring(1).lowercase(),
                            type = KeywordData.Type.INSENSITIVE
                        )
                    }

                    else -> {
                        KeywordData(
                            word = word,
                            type = KeywordData.Type.CASESENSITIVE
                        )
                    }
                }
            }
    }
}