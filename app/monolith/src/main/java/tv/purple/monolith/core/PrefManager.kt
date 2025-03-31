package tv.purple.monolith.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.models.flag.FlagListener
import tv.purple.monolith.core.models.flag.core.BooleanValue
import tv.purple.monolith.core.models.flag.core.IntRangeValue
import tv.purple.monolith.core.models.flag.core.IntValue
import tv.purple.monolith.core.models.flag.core.ListValue
import tv.purple.monolith.core.models.flag.core.ObjectValue
import tv.purple.monolith.core.models.flag.core.StringValue
import tv.twitch.android.app.core.ThemeManager
import java.util.concurrent.atomic.AtomicBoolean

@SuppressLint("StaticFieldLeak")
@Suppress("DEPRECATION")
/**
 *  This is a singleton class that manages and obtains the mod and some app preferences.
 *  It must be initialized before any other components or features, and has its own lifecycle.
 */
object PrefManager : SharedPreferences.OnSharedPreferenceChangeListener {
    private const val PTV_SHARED_PREFERENCES_NAME = "orange"

    private val twitch by lazy { getTwitchSharedPreferences(context) }
    private val ptv by lazy { getPTVSharedPreferences(context) }
    private val chatSettings by lazy { getChatSettings(context) }

    // Store user chomment settings for the current app session
    // String - vodId, Int - diff
    private val chommentSeekerCache = mutableMapOf<String, Int>()

    // Cache dark theme flag
    var isDarkThemeEnabled = false

    var isAnimatedEmotesEnabled = true

    private val listeners = mutableSetOf<FlagListener>()

    // Check is PrefManager already initialized
    private var isInitialized = AtomicBoolean(false)

    private lateinit var context: Context

    private val logger = LoggerWithTag("PrefManager")

    @SuppressLint("LogNotTimber")
    fun init(twitchApp: Context) {
        if (isInitialized.getAndSet(true)) {
            logger.warning("The second attempt to init the PrefManager!")
            return
        }

        context = twitchApp.applicationContext

        logger.info("Initializing...")
        Flag.values().forEach { flag ->
            setFromPreferences(flag = flag)
        }

        ptv.registerOnSharedPreferenceChangeListener(this)
        twitch.registerOnSharedPreferenceChangeListener(this)
        chatSettings.registerOnSharedPreferenceChangeListener(this)

        isAnimatedEmotesEnabled = chatSettings.getBoolean("is_animated_emotes_enabled", true)

        logger.info("Done!")
    }

    /**
     * Call this after ThemeManager.Companion.setTheme(application);
     */
    fun fixDarkThemeInit() {
        isDarkThemeEnabled = ThemeManager.Companion!!.isNightModeEnabled(context)
    }

    override fun onSharedPreferenceChanged(pref: SharedPreferences?, key: String?) {
        logger.debug { "pref --> $pref, key --> $key" }
        when (pref) {
            chatSettings -> onChatSettingsSharedPreferenceChanged(key = key)
            twitch -> onTwitchSharedPreferenceChanged(key = key)
            ptv -> onOrangeSharedPreferenceChanged(key = key)
            else -> {
                logger.warning("Unknown preference ref: $pref")
            }
        }
    }

    private fun onChatSettingsSharedPreferenceChanged(key: String?) {
        isAnimatedEmotesEnabled = chatSettings.getBoolean("is_animated_emotes_enabled", true)
    }

    private fun onTwitchSharedPreferenceChanged(key: String?) {
        isDarkThemeEnabled = ThemeManager.Companion!!.isNightModeEnabled(context)
    }

    private fun onOrangeSharedPreferenceChanged(key: String?) {
        key?.let { value ->
            Flag.findByKey(prefKey = value)?.let { flag ->
                setFromPreferences(flag = flag)
                listeners.forEach { it.onFlagValueChanged(flag = flag) }
            }
        }
    }

    private fun writeInt(flag: Flag, value: Int) {
        ptv.edit().putInt(flag.preferenceKey, value).apply()
    }

    private fun writeBoolean(flag: Flag, value: Boolean) {
        ptv.edit().putBoolean(flag.preferenceKey, value).apply()
    }

    private fun writeString(flag: Flag, value: String) {
        ptv.edit().putString(flag.preferenceKey, value).apply()
    }

    private fun readRawString(flag: Flag): String? {
        return ptv.getString(
            flag.preferenceKey,
            Flag.getString(holder = flag.defaultHolder)
        )
    }

    private fun readRawInt(flag: Flag): Int {
        return ptv.getInt(
            flag.preferenceKey,
            Flag.getInt(holder = flag.defaultHolder)
        )
    }

    private fun readRawBoolean(flag: Flag): Boolean {
        return ptv.getBoolean(
            flag.preferenceKey,
            Flag.getBoolean(holder = flag.defaultHolder)
        )
    }

    private fun setFromPreferences(flag: Flag) {
        when (val value = flag.valueHolder) {
            is BooleanValue -> flag.valueHolder.setValue(readRawBoolean(flag = flag))
            is IntValue -> flag.valueHolder.setValue(readRawInt(flag = flag))
            is StringValue -> flag.valueHolder.setValue(readRawString(flag = flag))
            is ListValue<*> -> flag.valueHolder.setValue(readRawString(flag = flag))
            is IntRangeValue -> flag.valueHolder.setValue(readRawInt(flag = flag))
            is ObjectValue<*> -> flag.valueHolder.setValue(readRawString(flag = flag))
            else -> throw IllegalStateException("Illegal subclass: ${value.javaClass}")
        }
    }

    private fun getPTVSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            PTV_SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }

    private fun getTwitchSharedPreferences(context: Context): SharedPreferences {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
    }

    private fun getChatSettings(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "ChatSettings",
            Context.MODE_PRIVATE
        )
    }

    fun setUserMentionColor(newColor: Int) {
        logger.debug("newColor: $newColor")
        writeInt(Flag.USER_MENTION_COLOR, newColor)
    }

    fun saveToPreferences(flag: Flag) {
        when (val value = flag.valueHolder) {
            is BooleanValue -> writeBoolean(flag = flag, flag.asBoolean())
            is IntValue -> writeInt(flag = flag, flag.asInt())
            is IntRangeValue -> writeInt(flag = flag, flag.asInt())
            is StringValue -> writeString(flag = flag, flag.asString())
            is ListValue<*> -> writeString(flag = flag, flag.asString())
            is ObjectValue<*> -> writeString(flag = flag, flag.asString())
            else -> throw IllegalStateException("Illegal subclass: ${value.javaClass}")
        }
    }

    fun getChatDelayForVod(videoId: String): Int {
        return chommentSeekerCache[videoId] ?: 0
    }

    fun setChatDelayForVod(videoId: String, value: Int) {
        chommentSeekerCache[videoId] = value
    }

    fun registerFlagListeners(vararg l: FlagListener) {
        l.forEach { listener ->
            listeners.add(listener)
        }
    }
}