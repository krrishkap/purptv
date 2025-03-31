package tv.purple.monolith.features.chat.data.mapper

import tv.purple.monolith.features.chat.bridge.PurpleTVEmotePickerEmoteModel
import tv.purple.monolith.features.chat.data.model.FavoriteEmote
import tv.purple.monolith.features.chat.db.entities.FavEmoteEntity
import tv.purple.monolith.models.wrapper.EmotePackageSet
import tv.twitch.android.models.emotes.EmoteModelAssetType
import javax.inject.Inject

class FavEmotesMapper @Inject constructor() {
    fun mapEmote(it: PurpleTVEmotePickerEmoteModel): FavoriteEmote {
        return FavoriteEmote(
            code = it.token,
            id = it.id,
            channelId = it.channelId,
            packageSet = it.packageSet,
            isAnimated = it.assetType == EmoteModelAssetType.ANIMATED
        )
    }

    fun mapEmote(it: FavoriteEmote): FavEmoteEntity {
        return FavEmoteEntity(
            emoteId = it.id,
            emoteCode = it.code,
            channelId = it.channelId.toString(),
            packageSet = it.packageSet.hash,
            isAnimated = it.isAnimated
        )
    }

    fun mapEmotes(it: List<FavEmoteEntity>): List<FavoriteEmote> {
        return it.map { entity ->
            FavoriteEmote(
                code = entity.emoteCode,
                id = entity.emoteId,
                channelId = entity.channelId.toInt(),
                packageSet = EmotePackageSet.fromHash(entity.packageSet),
                isAnimated = entity.isAnimated
            )
        }
    }
}