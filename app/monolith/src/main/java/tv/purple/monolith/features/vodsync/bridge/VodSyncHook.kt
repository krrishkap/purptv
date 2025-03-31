package tv.purple.monolith.features.vodsync.bridge

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.PrefManager
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.util.ViewUtil.changeVisibility
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate
import tv.twitch.android.feature.theatre.common.StreamSettings
import tv.twitch.android.models.videos.VodModel
import java.lang.Math.abs

object VodSyncHook {
    private const val MIN_OFFSET = -30
    private const val MAX_OFFSET = 90
    private val SEEK_BAR_OFFSET = abs(MIN_OFFSET)
    private val SEEK_BAR_MAX = MAX_OFFSET + abs(MIN_OFFSET)

    @JvmStatic
    fun hookChommentTimestamp(vodModel: VodModel?, timestamp: Int): Int {
        vodModel ?: return timestamp
        val savedValue = PrefManager.getChatDelayForVod(videoId = vodModel.id)
        return (timestamp + savedValue).coerceAtLeast(0)
    }

    @JvmStatic
    fun getChommentSeekerSection(delegate: BaseViewDelegate): ViewGroup =
        delegate.contentView.getView(resName = RES_STRINGS.stream_settings_fragment__chat_delay_section)

    @JvmStatic
    fun getChommentSeekerHeader(delegate: BaseViewDelegate): View =
        delegate.contentView.getView(resName = RES_STRINGS.stream_settings_fragment__chat_delay_header)

    @JvmStatic
    @SuppressLint("SetTextI18n")
    fun renderChommentSeekerSection(
        section: ViewGroup,
        header: View?,
        player: StreamSettings.ConfigurablePlayer
    ) {
        val seekbar = section.getView<SeekBar>(RES_STRINGS.stream_settings_fragment__chat_delay_seekbar) // TODO: Slider?
        val text = section.getView<TextView>(RES_STRINGS.stream_settings_fragment__chat_delay_text)

        val vodId = player.vod?.id ?: run { // check for clips, streams
            hideSection(section, header, seekbar)
            return
        }

        showSection(section, header)

        val savedValue = PrefManager.getChatDelayForVod(videoId = vodId)
        val clampedValue = savedValue.coerceIn(MIN_OFFSET, MAX_OFFSET)
        setupSeekBar(seekbar, clampedValue, vodId, text)
    }

    private fun hideSection(section: ViewGroup, header: View?, seekbar: SeekBar) {
        section.changeVisibility(false)
        header?.changeVisibility(false)
        seekbar.setOnSeekBarChangeListener(null)
    }

    private fun showSection(section: ViewGroup, header: View?) {
        section.changeVisibility(true)
        header?.changeVisibility(true)
    }

    private fun setupSeekBar(seekbar: SeekBar, savedValue: Int, vodId: String, text: TextView) {
        seekbar.apply {
            max = SEEK_BAR_MAX
            progress = savedValue + SEEK_BAR_OFFSET
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val currentSyncValue = progress - SEEK_BAR_OFFSET
                    val clampedValue = currentSyncValue.coerceIn(MIN_OFFSET, MAX_OFFSET)
                    PrefManager.setChatDelayForVod(videoId = vodId, value = clampedValue)
                    drawValue(text, clampedValue)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }
        drawValue(text, savedValue)
    }

    @SuppressLint("SetTextI18n")
    private fun drawValue(view: TextView, value: Int) {
        val formattedNumber = when {
            value > 0 -> "+$value"
            value < 0 -> "$value"
            else -> "0"
        }
        view.text = RES_STRINGS.purpletv_chat_delay_text_format.fromResToString(formattedNumber)
    }
}