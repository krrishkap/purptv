package tv.purple.monolith.features.updater.component.data.mapper

import tv.purple.monolith.features.updater.component.data.model.UpdateData
import tv.purple.monolith.models.retrofit.nop.UpdateChannelData
import javax.inject.Inject

class UpdaterDataMapper @Inject constructor() {
    fun map(data: UpdateChannelData?): UpdateData? {
        data ?: return null

        return UpdateData(
            build = data.build ?: -1,
            codename = data.codename ?: "Unknown",
            url = data.apkUrl!![0],
            logoUrl = data.logoUrl,
            changelog = data.changelog
        )
    }
}