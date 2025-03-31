package tv.purple.monolith.core.db

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import java.util.concurrent.Executors

class TinyDB<D>(
    context: Context,
    name: String,
    private val callback: Callback
) {
    private val preferences = context.getSharedPreferences("tinydb__$name", Context.MODE_PRIVATE)
    private val gson = Gson()

    interface Callback {
        fun invalidate()
    }

    private fun getObjectKey(key: String): String {
        if (key.startsWith(OBJ_PREF_KEY)) {
            return key
        }

        return OBJ_PREF_KEY + key
    }

    private fun putObject(key: String, data: D) {
        preferences.edit().putString(getObjectKey(key), gson.toJson(data)).commit()
    }

    private fun incAndPutObject(data: D) {
        val key = incIndex().toString()
        putObject(getObjectKey(key), data)
    }

    @SuppressLint("ApplySharedPref")
    fun removeObject(entity: Entity<D>) {
        executor.submit {
            val key = getObjectKey(entity.getKey())
            preferences.edit().remove(key).commit()
            callback.invalidate()
        }
    }

    @SuppressLint("ApplySharedPref")
    fun putObject(obj: D) {
        executor.submit {
            incAndPutObject(obj)
            callback.invalidate()
        }
    }

    fun putObjects(keywords: List<D>) {
        executor.submit {
            for (obj in keywords) {
                incAndPutObject(obj)
            }
            callback.invalidate()
        }
    }

    @SuppressLint("ApplySharedPref")
    fun updateObject(obj: Entity<D>) {
        executor.submit {
            putObject(obj.getKey(), obj.getData())
            callback.invalidate()
        }
    }

    fun getObject(probKey: String, classOfT: Class<D>): Entity<D>? {
        val key = getObjectKey(probKey)
        val json = preferences.getString(key, null) ?: return null
        val data = gson.fromJson(json, classOfT) ?: return null
        return EntityImpl(key, data)
    }

    fun getAll(classOfT: Class<D>?): List<Entity<D>> {
        return preferences.all.mapNotNull { entry ->
            val key = getObjectKey(entry.key)
            if (!key.startsWith(OBJ_PREF_KEY)) return@mapNotNull null

            val value = entry.value
            if (value is String) {
                if (!TextUtils.isEmpty(value)) {
                    val obj = gson.fromJson(value, classOfT) ?: return@mapNotNull null
                    return@mapNotNull EntityImpl<D>(key, obj)
                }
            }
            return@mapNotNull null
        }.sort()
    }

    @SuppressLint("ApplySharedPref")
    private fun incIndex(): Int {
        synchronized(lock) {
            var index = preferences.getInt(INDEX_KEY, 0)
            preferences.edit().putInt(INDEX_KEY, ++index).commit()
            return index
        }
    }

    companion object {
        private val lock = Any()
        private val executor = Executors.newSingleThreadExecutor()

        const val SYS_PREF_KEY = "sys_"
        const val OBJ_PREF_KEY = "obj_"
        const val INDEX_KEY = SYS_PREF_KEY + "index"
    }
}

private fun <E> List<Entity<E>>.sort(): List<Entity<E>> {
    return this.sortedBy {
        val key = it.getKey()
        key.substring(TinyDB.OBJ_PREF_KEY.length).toInt()
    }
}
