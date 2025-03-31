package tv.purple.monolith.features.settings.bridge.slider

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.ResManager.fromResToLayoutId
import tv.purple.monolith.core.compat.ClassCompat
import tv.purple.monolith.core.util.ViewUtil.changeVisibility
import tv.twitch.android.core.adapters.ModelRecyclerAdapterItem
import tv.twitch.android.core.adapters.ViewHolderGenerator

class SliderRecyclerItem(
    context: Context,
    model: SliderModel
) : ModelRecyclerAdapterItem<SliderModel>(context, model) {

    override fun bindToViewHolder(vh: RecyclerView.ViewHolder) {
        ClassCompat.invokeIf<SliderRecyclerItemViewHolder>(vh) {
            it.title.text = model.primaryText
            if (model.secondaryText.isBlank()) {
                it.desc.changeVisibility(false)
            } else {
                it.desc.text = model.secondaryText
                it.desc.changeVisibility(true)
            }

            val range = model.flag.asIntRange()

            it.slider.valueFrom = range.minValue.toFloat()
            it.slider.valueTo = range.maxValue.toFloat()
            it.slider.value = model.flag.asInt().toFloat()
            it.slider.stepSize = range.step.toFloat()
        }
    }

    override fun getViewHolderResId(): Int {
        return RES_STRINGS.purpletv_settings_slider.fromResToLayoutId()
    }

    override fun newViewHolderGenerator(): ViewHolderGenerator {
        return ViewHolderGenerator { SliderRecyclerItemViewHolder(it, model.listener) }
    }
}