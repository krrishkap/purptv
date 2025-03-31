package tv.purple.monolith.component.api.repository

import io.reactivex.Single
import tv.purple.monolith.component.api.data.source.PronounsApiSource
import tv.purple.monolith.models.data.UserPronoun
import tv.purple.monolith.models.retrofit.alejo.PronounItemData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PronounsRepository @Inject constructor(
    private val source: PronounsApiSource
) {
    fun getPronouns(): Single<List<PronounItemData>> {
        return source.getPronouns()
    }

    fun getUserPronoun(userName: String): Single<UserPronoun> {
        return source.getUserPronoun(userName = userName)
    }
}