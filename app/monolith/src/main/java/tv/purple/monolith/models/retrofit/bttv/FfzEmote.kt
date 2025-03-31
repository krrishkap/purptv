package tv.purple.monolith.models.retrofit.bttv

import android.util.ArrayMap
import tv.purple.monolith.models.retrofit.bttv.FfzImageType

data class FfzEmote(
    val id: Int,
    val code: String,
    val images: ArrayMap<String, String?>,
    val imageType: FfzImageType
)
