package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import tv.purple.monolith.models.retrofit.gql.chapters.ChaptersData
import tv.purple.monolith.models.retrofit.gql.chapters.ChaptersRequest
import tv.purple.monolith.models.retrofit.gql.logs.DataResponse
import tv.purple.monolith.models.retrofit.gql.logs.ModLogsData
import tv.purple.monolith.models.retrofit.gql.logs.ModLogsRequest
import tv.purple.monolith.models.retrofit.gql.logs.UserInfoData
import tv.purple.monolith.models.retrofit.gql.logs.UserInfoRequest

interface TwitchGQLApi {
    @POST(".")
    @Headers("Content-Type: application/json")
    fun requestModLogs(@Body requestData: ModLogsRequest): Single<DataResponse<ModLogsData>>

    @POST(".")
    @Headers("Content-Type: application/json")
    fun requestUserInfo(@Body requestData: UserInfoRequest): Single<DataResponse<UserInfoData>>

    @POST(".")
    @Headers("Content-Type: application/json")
    fun requestChapters(@Body requestData: ChaptersRequest): Single<DataResponse<ChaptersData>>
}