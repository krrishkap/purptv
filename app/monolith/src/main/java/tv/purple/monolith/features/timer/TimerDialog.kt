package tv.purple.monolith.features.timer

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.inflate
import tv.purple.monolith.features.timer.service.TimerService


class TimerDialog : DialogFragment() {
    companion object {
        private const val MAX_MINUTES_VALUE = 59
        private const val MAX_HOURS_VALUE = 23
        private const val MIN_MINUTES_VALUE = 0
        private const val MIN_HOURS_VALUE = 0

        private const val DIALOG_TAG = RES_STRINGS.purpletv_timer_tag

        fun createAndShowTimerDialog(fragmentActivity: FragmentActivity) {
            TimerDialog().show(
                fragmentActivity.supportFragmentManager,
                DIALOG_TAG
            )
        }
    }

    private lateinit var hoursPicker: NumberPicker
    private lateinit var minutesPicker: NumberPicker

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            val view = requireContext().inflate(RES_STRINGS.purpletv_dialog_timer)

            hoursPicker = view.getView(RES_STRINGS.purpletv_dialog_timer__hours)
            minutesPicker = view.getView(RES_STRINGS.purpletv_dialog_timer__minutes)
            val okButton = view.getView<Button>(RES_STRINGS.purpletv_dialog_timer__ok)
            val cancelButton = view.getView<Button>(RES_STRINGS.purpletv_dialog_timer__cancel)

            minutesPicker.minValue = MIN_MINUTES_VALUE
            minutesPicker.maxValue = MAX_MINUTES_VALUE
            hoursPicker.minValue = MIN_HOURS_VALUE
            hoursPicker.maxValue = MAX_HOURS_VALUE

            okButton.setOnClickListener {
                val minutes = minutesPicker.value
                val hours = hoursPicker.value
                val totalSeconds = minutes * 60 + hours * 3600
                if (totalSeconds <= 0) {
                    Toast.makeText(context, "Can't set 0", Toast.LENGTH_SHORT).show()
                } else {
                    startTimerService(totalSeconds)
                    dialog?.dismiss()
                }
            }
            cancelButton.setOnClickListener {
                dialog?.dismiss()
            }
            builder.setView(view).create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun startTimerService(totalSeconds: Int) {
        TimerService.createAndStartService(
            context = requireContext(),
            totalSeconds = totalSeconds
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("hours", hoursPicker.value)
        outState.putInt("minutes", minutesPicker.value)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            hoursPicker.value = it.getInt("hours")
            minutesPicker.value = it.getInt("minutes")
        }
    }
}