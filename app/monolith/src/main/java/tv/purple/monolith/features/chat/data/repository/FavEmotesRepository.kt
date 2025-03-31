package tv.purple.monolith.features.chat.data.repository

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import tv.purple.monolith.features.chat.bridge.PurpleTVEmotePickerEmoteModel
import tv.purple.monolith.features.chat.data.model.FavoriteEmote
import tv.purple.monolith.features.chat.data.source.FavEmotesDataSource
import javax.inject.Inject

class FavEmotesRepository @Inject constructor(
    val source: FavEmotesDataSource,
    val scheduler: Scheduler
) {
    private val disposables = CompositeDisposable()

    fun getEmotesForChannel(channelId: Int): Single<List<FavoriteEmote>> {
        if (channelId == 0 || channelId == -1) {
            return Single.just(emptyList())
        }
        return source.getEmotesForChannel(channelId)
    }

    fun deleteEmote(emote: FavoriteEmote) {
        disposables.add(
            source.delete(
                emote = emote
            ).subscribeOn(scheduler).subscribe({}, { it.printStackTrace() })
        )
    }

    fun deleteEmote(emote: PurpleTVEmotePickerEmoteModel) {
        disposables.add(
            source.delete(
                emote = emote
            ).subscribeOn(scheduler).subscribe({}, { it.printStackTrace() })
        )
    }

    fun addEmote(favEmote: FavoriteEmote) {
        disposables.add(
            source.addEmote(favEmote).subscribeOn(scheduler).subscribe({}, { it.printStackTrace() })
        )
    }
}