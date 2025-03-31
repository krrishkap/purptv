package tv.purple.monolith.features.settings.bridge.model

import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.models.flag.Flag

class FlagToggleMenuModelExt(
    private val flag: Flag
) : ToggleMenuModelExt(
    primaryText = flag.titleResName.fromResToString(),
    secondaryText = flag.summaryResName?.fromResToString(),
    auxiliaryText = null,
    state = flag.asBoolean()
) {
    init {
        orangeKey = flag.preferenceKey
    }

    override fun getToggleState(): Boolean {
        return flag.asBoolean()
    }
}