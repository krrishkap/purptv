package tv.purple.monolith.core.util

import io.reactivex.Single
import io.reactivex.disposables.Disposable

object RxUtil {
    fun <T> Single<T>.nRetry(count: Int, errorCallback: (th: Throwable?) -> Unit): Single<T> {
        return this.retryWhen { errors ->
            return@retryWhen errors.take(count.toLong()).doOnNext {
                errorCallback(it)
            }
        }
    }

    fun Disposable.safeDispose() {
        if (!isDisposed) {
            synchronized(this) {
                if (!isDisposed) {
                    dispose()
                }
            }
        }
    }
}