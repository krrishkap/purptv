package tv.purple.monolith.core.util

import android.content.Context
import org.json.JSONObject
import tv.purple.monolith.core.Config.BUILD_JSON_FILENAME
import tv.purple.monolith.core.Config.USER_AGENT_TEMPLATE
import tv.purple.monolith.core.LoggerImpl
import tv.purple.monolith.models.util.PurpleTVBuildConfig
import java.io.IOException
import java.util.Locale

object PurpleBuildConfigUtil {
    private lateinit var buildConfig: PurpleTVBuildConfig

    fun init(context: Context) {
        buildConfig = loadBuildConfig(context)
    }

    private fun loadBuildConfig(context: Context): PurpleTVBuildConfig {
        return try {
            val rawJson = getRawBuildString(context) ?: return PurpleTVBuildConfig()
            val jsonObject = JSONObject(rawJson)

            PurpleTVBuildConfig(
                number = jsonObject.optInt("number", 0),
                version = jsonObject.optInt("version", 0),
                timestamp = jsonObject.optLong("timestamp", 0L),
                apiKey = jsonObject.optString("bugApiKey", ""),
                revision = jsonObject.optInt("revision", 0)
            )
        } catch (e: Exception) {
            LoggerImpl.error("Failed to load build config: ${e.message}")
            PurpleTVBuildConfig()
        }
    }

    @JvmStatic
    val buildConfigVariant
        get() = buildConfig

    @JvmStatic
    val buildNumber: Int
        get() = buildConfig.number

    @JvmStatic
    val userAgent by lazy {
        String.format(
            Locale.ENGLISH,
            USER_AGENT_TEMPLATE,
            buildConfig.getVersion(),
            buildConfig.number.toString()
        )
    }

    private fun getRawBuildString(context: Context): String? {
        return try {
            context.assets.open(BUILD_JSON_FILENAME).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            LoggerImpl.warning("Failed to read build.json: ${e.message}")
            null
        }
    }
}