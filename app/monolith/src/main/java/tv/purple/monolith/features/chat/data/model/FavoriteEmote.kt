package tv.purple.monolith.features.chat.data.model

import tv.purple.monolith.models.wrapper.EmotePackageSet

data class FavoriteEmote(
    val code: String,
    val id: String?,
    val channelId: Int,
    val packageSet: EmotePackageSet,
    val isAnimated: Boolean
) {
    fun isValidTwitchEmote(): Boolean {
        id ?: return false

        if (packageSet == EmotePackageSet.TwitchGlobal ||
            packageSet == EmotePackageSet.TwitchChannel
        ) {
            if (id.isBlank()) {
                return false;
            }

            if (id == "0" || id == "-1") {
                return false
            }

            return true
        }

        return false
    }
}