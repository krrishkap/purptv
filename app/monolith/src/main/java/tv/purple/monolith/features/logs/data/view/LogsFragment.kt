package tv.purple.monolith.features.logs.data.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import tv.purple.monolith.bridge.PurpleTVAppContainer
import tv.purple.monolith.bridge.di.TwitchActivityComponent
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.inflate
import tv.purple.monolith.features.logs.component.data.model.MessageItem
import tv.purple.monolith.features.logs.component.data.repository.LogsRepository
import tv.purple.monolith.features.logs.data.adapter.LogsAdapter
import tv.twitch.android.core.activities.TwitchHiltActivity
import tv.twitch.android.shared.chat.messages.data.ChatMessageV2Parser
import javax.inject.Inject

class LogsFragment @Inject constructor(
    private val logsRepository: LogsRepository,
    private val logsAdapter: LogsAdapter
) : BottomSheetDialogFragment() {
    private lateinit var rv: RecyclerView
    private lateinit var pb: ProgressBar

    private val disposables = CompositeDisposable()

    private lateinit var twitchActivityComponent: TwitchActivityComponent
    private lateinit var parser: ChatMessageV2Parser

    fun bind(fragment: FragmentActivity, channelId: Int) {
        val factory = PurpleTVAppContainer.getInstance().provideActivityFactory()
        twitchActivityComponent = factory.create(
            activity = fragment as TwitchHiltActivity
        )
        parser = twitchActivityComponent.provideChatMessageV2Parser()

        logsAdapter.bindFactory(twitchActivityComponent.provideChatMessageFactoryV2(), channelId)
    }

    private fun render(messages: List<MessageItem>) {
        logsAdapter.setData(messages)
        if (messages.isEmpty()) {
            pb.visibility = View.VISIBLE
            rv.visibility = View.GONE
            Toast.makeText(requireContext(), "No messages!", Toast.LENGTH_SHORT).show()
        } else {
            pb.visibility = View.GONE
            rv.visibility = View.VISIBLE
        }
    }

    fun loadTwitchLogs(username: String, channelId: String) {
        disposables.clear()
        disposables.add(
            logsRepository.getTwitchLogs(
                parser = parser, username = username, channelId = channelId
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ render(it) }, Throwable::printStackTrace)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.context.inflate(
            parent = container, resName = "purpletv_logs_container"
        )

        pb = view.getView(resName = "purpletv_logs_container__pb")
        rv = view.getView<RecyclerView>(resName = "purpletv_logs_container__rv").apply {
            adapter = logsAdapter
        }

        return view
    }
}