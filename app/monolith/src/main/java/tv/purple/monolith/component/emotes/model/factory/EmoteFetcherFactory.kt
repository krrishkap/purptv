package tv.purple.monolith.component.emotes.model.factory

import io.reactivex.Single
import tv.purple.monolith.models.data.emotes.EmoteSet
import tv.purple.monolith.core.net.Fetcher

data class EmoteFetcherFactory(
    private val provider: () -> Single<EmoteSet>
) : Fetcher.ISourceFactory<EmoteSet> {
    override fun create(): Single<EmoteSet> {
        return provider()
    }
}