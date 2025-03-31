package tv.purple.monolith.core.net

import io.reactivex.Single
import tv.purple.monolith.models.util.RefreshPolice

interface Fetcher<Data> {
    fun refresh(refreshPolicy: RefreshPolice = RefreshPolice.DEFAULT)
    fun clear()
    fun getData(): Data?

    interface ISourceFactory<Data> {
        fun create(): Single<Data>
    }
}