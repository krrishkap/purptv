package tv.purple.monolith.models.data.badges.impl

import android.graphics.Color
import tv.purple.monolith.core.util.FileUtil.toHex
import tv.purple.monolith.models.data.badges.Badge
import java.util.UUID

data class BadgeHomiesImpl(
    private val badgeId: UUID,
) : Badge {
    override fun getCode(): String = "homies"
    override fun getUrl(): String = "$CDN_URL${badgeId.toHex()}"
    override fun getBackgroundColor(): Int = Color.TRANSPARENT
    override fun getReplaces(): String? = null

    companion object {
        private const val CDN_URL = "https://api.nopbreak.ru/purpletv/h4/badge/"
    }
}