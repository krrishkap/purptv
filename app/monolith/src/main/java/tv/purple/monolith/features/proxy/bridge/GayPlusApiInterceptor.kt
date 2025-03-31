package tv.purple.monolith.features.proxy.bridge

import okhttp3.Interceptor
import okhttp3.Response
import java.util.regex.Pattern

class GayPlusApiInterceptor : BaseInterceptor(
    pattern = Pattern.compile("https://gql.twitch.tv/gql"),
    debugInjection = true
) {
    override fun intercept(chain: Interceptor.Chain): Response {
//        val headers = chain.request().headers
//        val operationName = headers["X-APOLLO-OPERATION-NAME"]
//        if (operationName != null) {
//            if (operationName == "StreamAccessTokenQuery") {
//                return super.intercept(chain)
//            }
//        }

        return chain.proceed(chain.request())
    }

    override fun getHeaders(): Map<String, String> {
        return mapOf()
    }
}