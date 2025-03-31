package tv.purple.monolith.features.chat.data.source

import io.reactivex.Completable
import io.reactivex.Single
import tv.purple.monolith.features.chat.bridge.PurpleTVEmotePickerEmoteModel
import tv.purple.monolith.features.chat.data.mapper.FavEmotesMapper
import tv.purple.monolith.features.chat.data.model.FavoriteEmote
import tv.purple.monolith.features.chat.db.FavEmotesDatabase
import javax.inject.Inject

class FavEmotesDataSource @Inject constructor(
    val mapper: FavEmotesMapper,
    val db: FavEmotesDatabase
) {
    fun addEmote(emote: FavoriteEmote): Completable {
        return db.favEmotesDAO().insert(mapper.mapEmote(emote))
    }

    fun delete(emote: PurpleTVEmotePickerEmoteModel): Completable {
        return delete(mapper.mapEmote(emote))
    }

    fun delete(emote: FavoriteEmote): Completable {
        return if (emote.id != null && emote.isValidTwitchEmote()) {
            db.favEmotesDAO().deleteById(
                id = emote.id,
                channelId = emote.channelId.toString(),
                type = emote.packageSet.hash
            )
        } else {
            db.favEmotesDAO().delete(
                code = emote.code,
                channelId = emote.channelId.toString(),
                type = emote.packageSet.hash
            )
        }
    }

    fun getEmotesForChannel(channelId: Int): Single<List<FavoriteEmote>> {
        return db.favEmotesDAO().getForChannel(
            channelId = channelId.toString()
        ).map(mapper::mapEmotes)
    }
}