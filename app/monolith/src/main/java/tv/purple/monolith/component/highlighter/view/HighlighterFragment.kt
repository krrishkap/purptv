package tv.purple.monolith.component.highlighter.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.component.cp.ColorPickerDialog
import tv.purple.monolith.component.highlighter.data.model.KeywordData
import tv.purple.monolith.component.highlighter.data.repository.HighlighterRepository
import tv.purple.monolith.component.highlighter.mvp.HighlighterContract
import tv.purple.monolith.component.highlighter.presenter.HighlighterPresenter
import tv.purple.monolith.component.highlighter.util.SwipeToDeleteCallback
import tv.purple.monolith.core.db.Entity
import tv.purple.monolith.core.util.ViewUtil.changeVisibility
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.inflate
import tv.purple.monolith.features.ui.misc.HighlighterAdapter
import javax.inject.Inject

class HighlighterFragment @Inject constructor(
    repository: HighlighterRepository
) : Fragment(), HighlighterContract.View, ClickListener {
    private lateinit var rv: RecyclerView
    private lateinit var loading: ProgressBar
    private lateinit var addButton: TextView

    private val presenter = HighlighterPresenter(repository)
    private val adapter = HighlighterAdapter(this)

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.context.inflate(
            resName = RES_STRINGS.purpletv_highlighter_xml,
            parent = container
        )

        addButton = view.getView(RES_STRINGS.purpletv_highlighter__add_button)
        loading = view.getView(RES_STRINGS.purpletv_highlighter__loading)
        rv = view.getView(RES_STRINGS.purpletv_highlighter__rv)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this.context)

        val swipeHandler = object : SwipeToDeleteCallback(inflater.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.absoluteAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    adapter.getItem(pos)?.let { keyword ->
                        presenter.onItemRemoved(keyword)
                    } ?: adapter.removeAt(pos)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rv)

        addButton.setOnClickListener { presenter.onAddButtonClicked() }

        return view
    }

    override fun render(state: HighlighterContract.View.State) {
        when (state) {
            HighlighterContract.View.State.Loading -> {
                loading.changeVisibility(isVisible = true)
                rv.changeVisibility(isVisible = false)
            }

            is HighlighterContract.View.State.Update -> {
                adapter.setData(state.keywords)
                loading.changeVisibility(isVisible = false)
                rv.changeVisibility(isVisible = true)
            }
        }
    }

    override fun showAddKeywordsDialog() {
        val editText = EditText(requireActivity())
        val dialog = AlertDialog.Builder(requireActivity())
            .setView(editText)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val rawText = editText.text.toString().trim()
                presenter.addNewItems(rawText)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
        dialog.show()
    }

    override fun showChangeItemColorDialog(keyword: Entity<KeywordData>) {
        ColorPickerDialog(
            startColor = keyword.getData().color
        ) { color ->
            presenter.onItemColorChanged(
                keyword = keyword,
                newColor = color
            )
        }.show(requireActivity().supportFragmentManager, RES_STRINGS.purpletv_change_color_tag)
    }

    override fun onChangeColorClicked(item: Entity<KeywordData>) {
        presenter.onChangeColorItemClicked(keyword = item)
    }

    override fun onVibrationClicked(item: Entity<KeywordData>) {
        presenter.onVibrationItemClicked(keyword = item)
    }

    override fun onChangeTypeClicked(item: Entity<KeywordData>) {
        presenter.onChangeTypeItemClicked(keyword = item)
    }
}