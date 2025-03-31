package tv.purple.monolith.features.settings.bridge.slider

import android.view.View
import android.widget.TextView
import com.google.android.material.slider.Slider
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.twitch.android.core.adapters.AbstractTwitchRecyclerViewHolder
import tv.twitch.android.core.adapters.RecyclerAdapterItem

class SliderRecyclerItemViewHolder(
    view: View,
    private val listener: SliderModel.SliderListener
) : AbstractTwitchRecyclerViewHolder(view) {
    val title: TextView = view.getView("menu_item_title")
    val desc: TextView = view.getView("menu_item_description")
    val valueLabel: TextView = view.getView(RES_STRINGS.purpletv_settings_slider__value)
    val slider: Slider = view.getView(RES_STRINGS.purpletv_settings_slider__slider)

    var item: SliderRecyclerItem? = null

    override fun onBindDataItem(p0: RecyclerAdapterItem?) {
        super.onBindDataItem(p0)
        if (p0 is SliderRecyclerItem) {
            item = p0
        }
        slider.clearOnChangeListeners()
        slider.addOnChangeListener { _, value, _ ->
            item?.let { i ->
                valueLabel.text = value.toInt().toString()
                listener.onSliderValueChanged(i.model.flag, value.toInt())
            }
        }
        valueLabel.text = slider.value.toInt().toString()
    }

    override fun onRecycled() {
        slider.clearOnChangeListeners()
        super.onRecycled()
    }
}