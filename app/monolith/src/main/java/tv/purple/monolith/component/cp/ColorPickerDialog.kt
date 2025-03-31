package tv.purple.monolith.component.cp

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.slider.Slider
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.inflate

class ColorPickerDialog(
    private val startColor: Int = Color.BLACK,
    private val listener: (Int) -> Unit
) : DialogFragment() {
    private lateinit var colorPreview: View
    private lateinit var seekRed: Slider
    private lateinit var seekGreen: Slider
    private lateinit var seekBlue: Slider
    private lateinit var seekAlpha: Slider
    private lateinit var btnSelect: Button

    @ColorInt
    private fun getCurrentColor(): Int = Color.argb(
        Math.round(seekAlpha.value),
        Math.round(seekRed.value),
        Math.round(seekGreen.value),
        Math.round(seekBlue.value)
    )

    private fun updateColorView() {
        colorPreview.setBackgroundColor(getCurrentColor())
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val view = requireContext().inflate(RES_STRINGS.purpletv_dialog_color_picker)

        colorPreview = view.getView(RES_STRINGS.purpletv_dialog_color_picker__view)
        seekRed = view.getView(RES_STRINGS.purpletv_dialog_color_picker__sred)
        seekGreen = view.getView(RES_STRINGS.purpletv_dialog_color_picker__sgreen)
        seekBlue = view.getView(RES_STRINGS.purpletv_dialog_color_picker__sblue)
        seekAlpha = view.getView(RES_STRINGS.purpletv_dialog_color_picker__alpha)
        btnSelect = view.getView(RES_STRINGS.purpletv_dialog_color_picker__select)

        seekAlpha.value = Color.alpha(startColor).toFloat()
        seekRed.value = Color.red(startColor).toFloat()
        seekGreen.value = Color.green(startColor).toFloat()
        seekBlue.value = Color.blue(startColor).toFloat()

        seekAlpha.addOnChangeListener(createSeekBarListener { updateColorView() })
        seekRed.addOnChangeListener(createSeekBarListener { updateColorView() })
        seekGreen.addOnChangeListener(createSeekBarListener { updateColorView() })
        seekBlue.addOnChangeListener(createSeekBarListener { updateColorView() })

        btnSelect.setOnClickListener {
            val selectedColor = Color.argb(
                seekAlpha.value.toInt(),
                seekRed.value.toInt(),
                seekGreen.value.toInt(),
                seekBlue.value.toInt()
            )
            listener(selectedColor)
            dismiss()
        }

        builder.setView(view)
        updateColorView()

        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        seekAlpha.clearOnChangeListeners()
        seekRed.clearOnChangeListeners()
        seekGreen.clearOnChangeListeners()
        seekBlue.clearOnChangeListeners()
    }

    private fun createSeekBarListener(updateColor: () -> Unit): Slider.OnChangeListener {
        return Slider.OnChangeListener { _, _, _ -> updateColor() }
    }
}