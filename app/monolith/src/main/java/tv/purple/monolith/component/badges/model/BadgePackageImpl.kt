package tv.purple.monolith.component.badges.model

import tv.purple.monolith.models.wrapper.BadgePackageSet
import tv.purple.monolith.models.util.RefreshPolice
import tv.purple.monolith.models.data.badges.Badge
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.core.LoggerWithTag
import tv.purple.monolith.core.net.DataFetcher
import tv.purple.monolith.core.net.Fetcher

class BadgePackageImpl(
    source: Fetcher.ISourceFactory<BadgeSet>,
    token: BadgePackageSet
) : DataFetcher<BadgeSet>(
    dataSourceFactory = source,
    logger = LoggerWithTag(token.toString())
), BadgePackage {
    override fun getBadges(userId: Int): Collection<Badge> {
        return getData()?.getBadges(userId = userId) ?: emptyList()
    }

    override fun refresh(force: Boolean) {
        if (force) {
            refresh(refreshPolicy = RefreshPolice.FORCED)
        } else {
            refresh(refreshPolicy = RefreshPolice.DEFAULT)
        }
    }

    override fun isEmpty(): Boolean {
        return getData()?.isEmpty() ?: true
    }

    override fun hasBadges(userId: Int): Boolean {
        return getData()?.hasBadges(userId = userId) ?: false
    }
}