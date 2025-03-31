package tv.purple.monolith.features.logs.view

import android.view.View
import android.widget.TextView
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.ResManager.fromResToId
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.features.logs.component.data.repository.LogsRepository
import tv.purple.monolith.features.logs.data.adapter.LogsAdapter
import tv.purple.monolith.features.logs.data.view.LogsFragment
import tv.twitch.android.shared.chat.moderation.ModerationActionBottomSheetViewDelegate
import tv.twitch.android.shared.chat.moderation.ModerationBottomSheetViewState
import tv.twitch.android.shared.ui.elements.bottomsheet.BottomSheetListItemModel
import javax.inject.Inject

class ViewFactory @Inject constructor(
    private val logsRepository: LogsRepository,
    private val logsAdapter: LogsAdapter
) {
    fun createModLogsButton(state: ModerationBottomSheetViewState): BottomSheetListItemModel<*> {
        return BottomSheetListItemModel(
            RES_STRINGS.chat_moderation_menu__mod_logs.fromResToId(),
            RES_STRINGS.purpletv_mod_logs.fromResToString(),
            true,
            ModerationActionBottomSheetViewDelegate.ModerationActionButtonEvent.ViewLogs(
                state.username,
                state.channelId
            ),
            null
        )
    }

    fun createLocalLogsButton(view: View): TextView {
        return view.getView(RES_STRINGS.chat_user_dialog_fragment_view__local_logs)
    }

    fun createLogsFragment(): LogsFragment {
        return LogsFragment(logsRepository, logsAdapter)
    }
}