package tv.purple.monolith.features.updater.component.data.repository

import io.reactivex.Single
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.models.flag.variants.UpdateChannel
import tv.purple.monolith.component.api.repository.NopRepository
import tv.purple.monolith.features.updater.component.data.mapper.UpdaterDataMapper
import tv.purple.monolith.features.updater.component.data.model.UpdateData
import tv.twitch.android.util.Optional
import javax.inject.Inject

class UpdaterRepository @Inject constructor(
    private val nopRepository: NopRepository,
    private val mapper: UpdaterDataMapper
) {
    fun observeUpdate(): Single<Optional<UpdateData>> {
        return nopRepository.getUpdateData()
            .map { data ->
                when (Flag.UPDATE_CHANNEL.asVariant<UpdateChannel>()) {
                    UpdateChannel.Release -> data.release
                    UpdateChannel.Dev -> data.dev
                    else -> null
                }?.let { channelData ->
                    Optional.of(channelData)
                } ?: Optional.empty()
            }.map {
                if (it.isPresent) {
                    val data = it.get()
                    if (!data.active) {
                        Optional.empty()
                    } else {
                        Optional.of(mapper.map(it.get()))
                    }
                } else {
                    Optional.empty()
                }
            }
    }
}