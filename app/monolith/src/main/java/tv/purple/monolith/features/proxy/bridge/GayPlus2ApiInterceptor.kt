package tv.purple.monolith.features.proxy.bridge

import java.util.regex.Pattern

class GayPlus2ApiInterceptor : BaseInterceptor(
    pattern = Pattern.compile("https://usher.ttvnw.net/"),
    debugInjection = true
) {
    override fun getHeaders(): Map<String, String> {
        return mapOf() // FIXME: new_version
    }
}