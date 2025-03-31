package tv.purple.monolith.features.settings.bridge.slider

import android.content.Context
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.models.flag.Flag
import tv.twitch.android.core.adapters.RecyclerAdapterItem
import tv.twitch.android.core.mvp.lifecycle.VisibilityProvider
import tv.twitch.android.shared.ui.menus.SettingActionListener
import tv.twitch.android.shared.ui.menus.core.MenuModel

class SliderModel(
    val flag: Flag,
    val listener: SliderListener
) : MenuModel.SingleItemModel(
    flag.titleResName.fromResToString(),
    flag.summaryResName?.fromResToString(),
    null,
    null,
    null,
    null
) {

    interface SliderListener {
        fun onSliderValueChanged(flag: Flag, value: Int)
    }

    override fun toRecyclerAdapterItem(
        p0: Context,
        p1: SettingActionListener?,
        p2: VisibilityProvider?
    ): RecyclerAdapterItem {
        return SliderRecyclerItem(p0, this)
    }
}