package tv.purple.monolith.features.settings.bridge.settings

import androidx.fragment.app.FragmentActivity
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.models.flag.core.BooleanValue
import tv.purple.monolith.core.models.flag.core.IntRangeValue
import tv.purple.monolith.core.models.flag.core.ListValue
import tv.purple.monolith.core.models.flag.variants.VoidVariant
import tv.purple.monolith.features.settings.bridge.factory.TwitchItemsFactory
import tv.purple.monolith.features.settings.bridge.model.DropDownMenuModelExt
import tv.purple.monolith.features.settings.bridge.model.FlagToggleMenuModelExt
import tv.purple.monolith.features.settings.bridge.model.Item
import tv.purple.monolith.features.settings.bridge.model.PurpleTVSubMenu
import tv.purple.monolith.features.settings.bridge.slider.SliderModel
import tv.purple.monolith.features.settings.component.PurpleTVSettingsController
import tv.twitch.android.shared.settings.BaseSettingsPresenter
import tv.twitch.android.shared.settings.SettingsNavigationController
import tv.twitch.android.shared.settings.SettingsTracker
import tv.twitch.android.shared.ui.menus.SettingsPreferencesController
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder
import tv.twitch.android.shared.ui.menus.core.MenuModel

abstract class BaseSettingsPresenter(
    activity: FragmentActivity,
    adapterBinder: MenuAdapterBinder,
    settingsTracker: SettingsTracker,
    private val controller: PurpleTVSettingsController,
    private val subMenuWrapper: PurpleTVSubMenu,
    private val factory: TwitchItemsFactory,
    private val itemFilter: (item: Item) -> Boolean = { true }
) : BaseSettingsPresenter(activity, adapterBinder, settingsTracker) {
    override fun getNavController(): SettingsNavigationController {
        return controller
    }

    override fun getPrefController(): SettingsPreferencesController {
        return controller
    }

    override fun getToolbarTitle(): String {
        return subMenuWrapper.title.fromResToString()
    }

    private fun itemToSettingModel(item: Item): MenuModel? {
        return when (item) {
            is Item.FlagItem ->
                mapper(
                    controller,
                    item.flag
                )

            is Item.InfoItem ->
                factory.createInfoMenuModel(
                    title = item.title.fromResToString(),
                    desc = item.desc?.fromResToString(),
                    func = {
                        item.behavior.onClick(context = activity.applicationContext)
                    })

            is Item.SubMenuItem ->
                factory.createSubMenuModel(
                    title = item.title.fromResToString(),
                    desc = item.desc?.fromResToString(),
                    dest = item.dest
                )
        }
    }

    override fun updateSettingModels() {
        settingModels.clear()
        subMenuWrapper.items.filter { itemFilter(it) }.forEach {
            itemToSettingModel(it)?.let { model ->
                settingModels.add(model)
            }
        }
    }

    companion object {
        private fun mapper(controller: PurpleTVSettingsController, flag: Flag): MenuModel? {
            return when (flag.valueHolder) {
                is BooleanValue -> FlagToggleMenuModelExt(flag)
                is ListValue<*> -> DropDownMenuModelExt<VoidVariant>(flag, controller)
                is IntRangeValue -> SliderModel(flag, controller)
                else -> null
            }
        }
    }

    fun addInfoMenu(title: String, desc: String?, func: () -> Unit) {
        settingModels.add(
            factory.createInfoMenuModel(
                title = title,
                desc = desc,
                func = func
            )
        )
    }
}