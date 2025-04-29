package tv.purple.monolith.models.wrapper

import org.json.JSONObject

data class EmoteCardModelWrapper(
    val token: String,
    val url: String,
    val emoteUrl: String,
    val set: EmotePackageSet
) {
    fun toJsonString(): String {
        return JSONObject().apply {
            put(JSON_KEY_TOKEN, token)
            put(JSON_KEY_URL, url)
            put(JSON_KEY_EMOTE_URL, emoteUrl)
            put(JSON_KEY_SET, set.name)
        }.toString()
    }

    companion object {
        private const val JSON_KEY_TOKEN = "token"
        private const val JSON_KEY_URL = "url"
        private const val JSON_KEY_EMOTE_URL = "emote_url"
        private const val JSON_KEY_SET = "set"

        fun fromString(str: String?): EmoteCardModelWrapper? {
            str?.toIntOrNull()?.let {
                return null
            }

            return try {
                if (str.isNullOrEmpty()) {
                    null
                } else {
                    with(JSONObject(str)) {
                        EmoteCardModelWrapper(
                            token = getString(JSON_KEY_TOKEN),
                            url = getString(JSON_KEY_URL),
                            emoteUrl = getString(JSON_KEY_EMOTE_URL),
                            set = EmotePackageSet.valueOf(getString(JSON_KEY_SET))
                        )
                    }
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}
