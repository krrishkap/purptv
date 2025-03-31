package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import tv.purple.monolith.component.api.data.mapper.PronounsApiMapper
import tv.purple.monolith.component.api.data.api.PronounsApi
import tv.purple.monolith.models.data.UserPronoun
import tv.purple.monolith.models.retrofit.alejo.PronounItemData
import javax.inject.Inject

class PronounsApiSource @Inject constructor(
    private val api: PronounsApi,
    private val mapper: PronounsApiMapper,
    private val scheduler: Scheduler
) {
    fun getPronouns(): Single<List<PronounItemData>> {
        return api.pronouns()
            .subscribeOn(scheduler)
    }

    fun getUserPronoun(userName: String): Single<UserPronoun> {
        return api.getUserPronoun(userName)
            .map { mapper.mapPronouns(userName, it) }
            .subscribeOn(scheduler)
    }
}