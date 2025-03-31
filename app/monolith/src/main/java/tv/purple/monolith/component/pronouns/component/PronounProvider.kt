package tv.purple.monolith.component.pronouns.component

import android.util.LruCache
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import tv.purple.monolith.component.api.repository.PronounsRepository
import tv.purple.monolith.core.Config.PRONOUN_MAX_CACHE_SIZE
import tv.purple.monolith.core.LoggerImpl
import tv.twitch.android.util.Optional
import tv.twitch.android.util.RxHelperKt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PronounProvider @Inject constructor(
    private val repository: PronounsRepository
) {
    private val cache: LruCache<String, Optional<String>> = LruCache(PRONOUN_MAX_CACHE_SIZE)
    private val idsMapper: HashMap<String, String> = hashMapOf()

    private val disposables = CompositeDisposable()

    fun destroy() {
        disposables.clear()
        cache.evictAll()
        idsMapper.clear()
    }

    fun updatePronounsMap() {
        val flow = RxHelperKt.async(repository.getPronouns()).subscribe({ items ->
            items.forEach { data ->
                idsMapper[data.name] = data.display
            }
        }, Throwable::printStackTrace)

        disposables.add(flow)
    }

    fun fetchPronounText(userName: String, function: (String) -> Unit) {
        val preparedUserName = userName.lowercase()

        cache.get(preparedUserName)?.let { optPronName ->
            if (!optPronName.isPresent) {
                return
            }

            val id = optPronName.get()

            idsMapper[id]?.let { display ->
                LoggerImpl.debug("[p] $preparedUserName --> $id --> $display")
                function.invoke(display)
                return
            }
        }

        val flow = repository.getUserPronoun(preparedUserName).doOnSuccess {
            it.pronounId?.let { id ->
                cache.put(preparedUserName, Optional.of(id))
            } ?: cache.put(preparedUserName, Optional.empty())
        }.observeOn(AndroidSchedulers.mainThread()).subscribe({
            val id = it.pronounId

            idsMapper[id]?.let { display ->
                LoggerImpl.debug("[p] $preparedUserName --> $id --> $display")
                function.invoke(display)
            }
        }, Throwable::printStackTrace)

        disposables.add(flow)
    }
}