package tv.purple.monolith.component.api.data.source

import io.reactivex.Single
import tv.purple.monolith.component.api.data.mapper.NopApiMapper
import tv.purple.monolith.models.data.Donation
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.twitch.android.app.core.ApplicationContext
import javax.inject.Inject

class NopFileDataSource @Inject constructor(
    private val mapper: NopApiMapper
) {

    fun getBadges(): Single<BadgeSet> {
        return Single.just(mapper.map(getLines()))
    }

    fun getDonators(): Single<List<Donation>> {
        return Single.just(mapper.mapDonators(getLines()))
    }

    companion object {
        private const val DONATIONS_ASSET_FILENAME = "donations.txt"
        private fun getLines(): List<String> {
            val context = ApplicationContext.getInstance().context

            return context.assets.open(DONATIONS_ASSET_FILENAME).bufferedReader().use { reader ->
                val result: MutableList<String> =
                    ArrayList()
                while (true) {
                    val line = reader.readLine() ?: break
                    result.add(line)
                }
                result
            }
        }
    }
}