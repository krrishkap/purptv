package tv.purple.monolith.component.blacklist.view

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
import tv.purple.monolith.component.blacklist.data.model.KeywordData
import tv.purple.monolith.component.blacklist.data.repository.BlacklistRepository
import tv.purple.monolith.component.blacklist.mvp.BlacklistContract
import tv.purple.monolith.component.blacklist.presenter.BlacklistPresenter
import tv.purple.monolith.component.blacklist.util.SwipeToDeleteCallback
import tv.purple.monolith.core.db.Entity
import tv.purple.monolith.core.util.ViewUtil.changeVisibility
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.inflate
import tv.purple.monolith.features.ui.misc.BlacklistAdapter

class BlacklistFragment : Fragment(), BlacklistContract.View, ClickListener {
    private lateinit var rv: RecyclerView
    private lateinit var loading: ProgressBar
    private lateinit var addButton: TextView

    private val presenter = BlacklistPresenter(this)
    private val adapter = BlacklistAdapter(this)

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.context.inflate(
            resName = "purpletv_highlighter",
            parent = container
        )

        addButton = view.getView("purpletv_highlighter__add_button")
        loading = view.getView("purpletv_highlighter__loading")
        rv = view.getView("purpletv_highlighter__rv")
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

    override fun render(state: BlacklistContract.View.State) {
        when (state) {
            BlacklistContract.View.State.Loading -> {
                loading.changeVisibility(isVisible = true)
                rv.changeVisibility(isVisible = false)
            }

            is BlacklistContract.View.State.Update -> {
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

    companion object {
        fun newInstance(repo: BlacklistRepository) = BlacklistFragment().apply {
            presenter.injectRepository(repo)
        }
    }

    override fun onChangeTypeClicked(item: Entity<KeywordData>) {
        presenter.onChangeTypeClicked(item)
    }
}