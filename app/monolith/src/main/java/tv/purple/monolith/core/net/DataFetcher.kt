package tv.purple.monolith.core.net

import io.reactivex.disposables.Disposable
import tv.purple.monolith.core.util.DateUtil
import tv.purple.monolith.core.util.RxUtil.safeDispose
import tv.purple.monolith.models.util.ILogger
import tv.purple.monolith.models.util.RefreshPolice
import java.util.concurrent.atomic.AtomicBoolean

abstract class DataFetcher<Data>(
    private val dataSourceFactory: Fetcher.ISourceFactory<Data>,
    private val maxRetry: Int = 3,
    private val logger: ILogger,
    private val minRefreshTimeoutSeconds: Int = 60
) : Fetcher<Data> {
    private var data: Data? = null

    private var fetchedAt: Long = System.currentTimeMillis()
    private val isFetching = AtomicBoolean(false)

    private var disposable: Disposable? = null

    override fun getData(): Data? = data

    override fun clear() {
        synchronized(this) {
            logger.debug("Clearing cached data and disposing active tasks")
            cancelAndClearTask()
            data = null
        }
    }

    private fun cancelAndClearTask() {
        synchronized(this) {
            disposable?.safeDispose()
            disposable = null
        }
    }

    private fun fetch() {
        logger.debug("Requesting data...")

        if (!isFetching.compareAndSet(false, true)) {
            logger.debug("Skipping fetch: already fetching")
            return
        }

        cancelAndClearTask()

        disposable = dataSourceFactory.create()
            .retryWhen { errors ->
                errors.take(maxRetry.toLong()).doOnNext {
                    logger.warning("Retry attempt...")
                }
            }
            .doFinally {
                isFetching.set(false)
            }
            .subscribe({ result ->
                handleSuccess(result)
            }, { error ->
                handleError(error)
            })
    }

    private fun handleSuccess(result: Data) {
        this.data = result
        this.fetchedAt = currentTime()
        logger.debug("Data fetched successfully: $result")
    }

    private fun handleError(error: Throwable) {
        logger.error("Failed to fetch data: ${error.message}")
        error.printStackTrace()
    }

    override fun refresh(refreshPolicy: RefreshPolice) {
        when (refreshPolicy) {
            RefreshPolice.DEFAULT -> {
                val diff = DateUtil.getSecondsBetween(fetchedAt, currentTime())
                if (data == null || diff > minRefreshTimeoutSeconds) {
                    logger.debug("Refreshing data...")
                    fetch()
                } else {
                    logger.debug("Skipping refresh: data is fresh (age: $diff seconds)")
                }
            }

            RefreshPolice.FORCED -> {
                logger.debug("Forced refresh triggered")
                fetch()
            }
        }
    }

    companion object {
        private fun currentTime() = System.currentTimeMillis()
    }
}

