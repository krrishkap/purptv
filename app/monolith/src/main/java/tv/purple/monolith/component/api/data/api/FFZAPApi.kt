package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import tv.purple.monolith.models.retrofit.ffzap.FfzAPBadge

interface FFZAPApi {
    @GET("/v1/supporters")
    fun supporters(): Single<List<FfzAPBadge>>
}