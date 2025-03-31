package tv.purple.monolith.core.compat

import tv.purple.monolith.core.LoggerImpl

object ClassCompat {
    inline fun <reified T> Any.cast(): T {
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T> callPrivateMethod(obj: Any?, methodName: String): T {
        return obj?.javaClass?.getDeclaredMethod(methodName).apply { this?.isAccessible = true }
            ?.invoke(obj) as T
    }

    inline fun <reified T> T.callPrivateMethod(methodName: String, vararg args: Any?): Any? {
        val clazz = T::class.java
        val method = if (args.isEmpty()) {
            clazz.getDeclaredMethod(methodName)
        } else {
            clazz.getDeclaredMethod(
                methodName,
                *args.map { it?.javaClass }.toTypedArray()
            )
        }.apply { isAccessible = true }

        return if (args.isEmpty()) {
            method.invoke(this)
        } else {
            method.invoke(this, *args)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> Any.getPrivateField(fieldName: String): T {
        return this::class.java.getDeclaredField(fieldName).apply {
            isAccessible = true
        }.get(this) as T
    }

    inline fun <reified T> invokeIf(obj: Any, function: (obj: T) -> Unit) {
        if (obj is T) {
            function.invoke(obj)
        } else {
            LoggerImpl.warning("Check cast: $obj")
        }
    }

    fun isOnStackTrace(clazz: String): Boolean {
        if (clazz.isBlank()) {
            return false
        }

        return Thread.currentThread().stackTrace.any { it.className.equals(clazz) }
    }
}