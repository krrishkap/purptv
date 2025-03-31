package tv.purple.monolith.features.chapters.data.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.bridge.ex.ISeekbarOverlayPresenter
import tv.purple.monolith.core.ResManager.fromResToId
import tv.purple.monolith.core.util.ViewUtil
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.inflate
import tv.purple.monolith.features.chapters.component.data.model.Chapter
import tv.purple.monolith.features.chapters.component.data.repository.ChaptersRepository
import tv.purple.monolith.features.chapters.data.adapter.ChaptersAdapter
import javax.inject.Inject

class ChaptersFragment @Inject constructor(
    val repository: ChaptersRepository
) : BottomSheetDialogFragment(), ChaptersAdapter.OnChapterClickedListener {
    lateinit var rv: RecyclerView
    private val adapter = ChaptersAdapter(this)
    private var seekPresenter: ISeekbarOverlayPresenter? = null

    private val disposables = CompositeDisposable()

    override fun onStart() {
        super.onStart()
        dialog?.let { dialog ->
            val id = "design_bottom_sheet".fromResToId()
            if (ViewUtil.isValidId(id)) {
                dialog.findViewById<View>(id)?.let { view ->
                    BottomSheetBehavior.from(view).setState(STATE_EXPANDED)
                }
            }
        }
    }

    fun load(vodId: String) {
        disposables.clear()
        disposables.add(
            repository.getChapters(vodId = vodId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::setData, Throwable::printStackTrace)
        )
    }

    fun bindSeekPresenter(presenter: ISeekbarOverlayPresenter) {
        seekPresenter = presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.context.inflate(
            parent = container,
            resName = RES_STRINGS.purpletv_chapters_container
        )

        rv = view.getView(resName = RES_STRINGS.purpletv_chapters_container__rv)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(
            inflater.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        return view
    }

    override fun onClicked(item: Chapter) {
        item.timestamp.let { timestamp ->
            if (timestamp >= 0) {
                seekPresenter?.xSeekTo(timestamp)
            }
        }

        dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        seekPresenter = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        seekPresenter = null
    }
}