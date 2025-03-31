package tv.purple.monolith.component.badges.model.factory

import io.reactivex.Single
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.core.net.Fetcher

data class BadgeFetcherFactoryImpl(
    private val provider: () -> Single<BadgeSet>
) : Fetcher.ISourceFactory<BadgeSet> {
    override fun create(): Single<BadgeSet> {
        return provider()
    }
}