package tv.purple.monolith.features.settings.bridge.settings

import androidx.fragment.app.FragmentActivity
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.models.flag.core.BooleanValue
import tv.purple.monolith.core.models.flag.core.IntRangeValue
import tv.purple.monolith.core.models.flag.core.ListValue
import tv.purple.monolith.core.models.flag.variants.MeStyle
import tv.purple.monolith.core.models.flag.variants.VoidVariant
import tv.purple.monolith.features.settings.bridge.factory.TwitchItemsFactory
import tv.purple.monolith.features.settings.bridge.model.DropDownMenuModelExt
import tv.purple.monolith.features.settings.bridge.model.FlagToggleMenuModelExt
import tv.purple.monolith.features.settings.bridge.model.Item
import tv.purple.monolith.features.settings.bridge.model.OrangeSubMenu
import tv.purple.monolith.features.settings.bridge.slider.SliderModel
import tv.purple.monolith.features.settings.component.OrangeSettingsController
import tv.twitch.android.shared.settings.BaseSettingsPresenter
import tv.twitch.android.shared.settings.SettingsNavigationController
import tv.twitch.android.shared.settings.SettingsTracker
import tv.twitch.android.shared.ui.menus.SettingsPreferencesController
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder
import tv.twitch.android.shared.ui.menus.core.MenuModel

open class BaseSettingsPresenter(
    activity: FragmentActivity,
    adapterBinder: MenuAdapterBinder,
    settingsTracker: SettingsTracker,
    private val orangeController: OrangeSettingsController,
    private val orangeSubMenuWrapper: OrangeSubMenu,
    private val factory: TwitchItemsFactory
) : BaseSettingsPresenter(activity, adapterBinder, settingsTracker) {
    override fun getNavController(): SettingsNavigationController {
        return orangeController
    }

    override fun getPrefController(): SettingsPreferencesController {
        return orangeController
    }

    override fun getToolbarTitle(): String {
        return orangeSubMenuWrapper.title.fromResToString()
    }

    private fun itemToSettingModel(item: Item): MenuModel? {
        return when (item) {
            is Item.FlagItem ->
                mapper(
                    orangeController,
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
        orangeSubMenuWrapper.items.forEach {
            itemToSettingModel(it)?.let { model ->
                settingModels.add(model)
            }
        }
    }

    companion object {
        private fun mapper(controller: OrangeSettingsController, flag: Flag): MenuModel? {
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