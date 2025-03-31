package tv.purple.monolith.component.api.data.source

import android.text.TextUtils
import android.webkit.URLUtil
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import tv.purple.monolith.component.api.data.api.NopApi
import tv.purple.monolith.component.api.data.mapper.NopApiMapper
import tv.purple.monolith.core.LoggerImpl
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.models.data.Donation
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.retrofit.nop.OrangeUpdateData
import tv.purple.monolith.models.retrofit.nop.PinnyInfo
import javax.inject.Inject

class NopApiDataSource @Inject constructor(
    private val api: NopApi,
    private val mapper: NopApiMapper,
    private val scheduler: Scheduler
) {
    fun getBadges(): Single<BadgeSet> {
        return api.donations()
            .subscribeOn(Schedulers.io())
            .map(mapper::map)
    }

    fun ping(build: Int, deviceId: String): Completable {
        return api.ping(
            build = build,
            deviceId = deviceId
        ).subscribeOn(scheduler)
    }

    fun pingInfo(build: Int): Single<PinnyInfo> {
        return api.pingInfo(build)
            .subscribeOn(scheduler)
    }

    fun getOrangeUpdate(): Single<OrangeUpdateData> {
        return api.update()
            .subscribeOn(scheduler)
    }

    fun getVodHunterPlaylist(vodId: Int): Single<Response<String>> {
        return api.vodhunter(vodId = vodId)
            .subscribeOn(scheduler)
    }

    fun getVodHunterPlaylist(payload: String): Single<Response<String>> {
        return api.vodhunter(payload = payload)
            .subscribeOn(scheduler)
    }

    fun getGayPlaylist(channelName: String): Single<Response<String>> {
        return api.getGayPlaylist(channel = channelName)
            .subscribeOn(scheduler)
    }

    fun getDonators(): Single<List<Donation>> {
        return api.donations()
            .subscribeOn(Schedulers.io())
            .map(mapper::mapDonators)
    }

    fun getCustomProxyResponse(channelName: String): Single<Response<String>> {
        val text = Flag.CUSTOM_PROXY_URL.asString().trim()
        if (TextUtils.isEmpty(text) || !URLUtil.isValidUrl(text)) {
            return Single.error(Exception("Invalid URL: ${text}"))
        }

        val url = text.replace("${'$'}channelName", channelName)
        LoggerImpl.debug("URL: $url")
        return api.getCustomProxyResponse(url)
            .subscribeOn(scheduler)
    }
}