package tv.purple.monolith.core

import javax.inject.Singleton

@Singleton
object Config {
    const val BUILD_JSON_FILENAME = "build.json"
    const val USER_AGENT_TEMPLATE = "PurpleTV/%s Build/%s"
    const val MAX_ROOM_CACHE_SIZE = 5
    const val PRONOUN_MAX_CACHE_SIZE = 5000
}