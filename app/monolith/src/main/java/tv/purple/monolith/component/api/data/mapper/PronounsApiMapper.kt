package tv.purple.monolith.component.api.data.mapper

import tv.purple.monolith.models.data.UserPronoun
import tv.purple.monolith.models.retrofit.alejo.UserPronounData
import javax.inject.Inject

class PronounsApiMapper @Inject constructor() {
    fun mapPronouns(
        userName: String,
        response: List<UserPronounData>
    ): UserPronoun {
        if (response.isEmpty()) {
            return UserPronoun(userName = userName)
        }

        return UserPronoun(userName = userName, pronounId = response[0].pronoun_id)
    }
}