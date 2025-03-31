package tv.purple.monolith.component.emotes.model

import tv.purple.monolith.core.LoggerWithTag
import tv.purple.monolith.models.wrapper.EmotePackageSet
import tv.purple.monolith.models.util.RefreshPolice
import tv.purple.monolith.core.net.DataFetcher
import tv.purple.monolith.models.data.emotes.Emote
import tv.purple.monolith.models.data.emotes.EmoteSet
import tv.purple.monolith.core.net.Fetcher

class EmotePackageImpl(
    source: Fetcher.ISourceFactory<EmoteSet>,
    private val token: EmotePackageSet
) : DataFetcher<EmoteSet>(
    dataSourceFactory = source,
    logger = LoggerWithTag(token.toString())
), EmotePackage {
    override fun getEmote(code: String): Emote? {
        return getData()?.getEmote(code = code)
    }

    override fun getEmotes(): Collection<Emote> {
        return getData()?.getEmotes() ?: emptyList()
    }

    override fun refresh(forced: Boolean) {
        if (forced) {
            refresh(refreshPolicy = RefreshPolice.FORCED)
        } else {
            refresh(refreshPolicy = RefreshPolice.DEFAULT)
        }
    }

    override fun isEmpty(): Boolean {
        return getData()?.isEmpty() ?: true
    }

    override fun getToken(): EmotePackageSet {
        return token
    }

    override fun hasEmote(code: String): Boolean {
        return getData()?.hasEmote(code) ?: false
    }
}