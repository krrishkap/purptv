package tv.purple.monolith.features.usersearch

import androidx.appcompat.widget.SearchView
import tv.purple.monolith.bridge.ex.IProxyEvent
import tv.purple.monolith.bridge.ex.IViewerListViewDelegate
import tv.purple.monolith.core.compat.ClassCompat
import tv.purple.monolith.core.compat.ClassCompat.cast
import tv.twitch.android.core.mvp.rxutil.DisposeOn
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateEvent
import tv.twitch.android.models.chat.Chatters
import tv.twitch.android.shared.chat.viewerlist.ViewerListPresenter
import tv.twitch.android.shared.chat.viewerlist.ViewerListViewDelegate


class UserSearch constructor(
    private val viewFactory: ViewFactory
) {
    fun getSearchBar(delegate: ViewerListViewDelegate): SearchView {
        return viewFactory.createSearchBar(delegate = delegate).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    ClassCompat.invokeIf<IViewerListViewDelegate>(delegate) {
                        it.onInputSearchTextChanged(newText)
                    }

                    return false
                }
            })
        }
    }

    fun filterChatters(chatters: Chatters?, searchText: String?): Chatters? {
        searchText ?: return chatters
        chatters ?: return null

        if (searchText.isBlank()) {
            return chatters
        }

        val broadcasters = filterUserList(chatters.broadcasters.cast(), searchText)
        val staff = filterUserList(chatters.staff.cast(), searchText)
        val mods = filterUserList(chatters.moderators.cast(), searchText)
        val vips = filterUserList(chatters.vips.cast(), searchText)
        val viewers = filterUserList(chatters.viewers.cast(), searchText)

        return Chatters(chatters.numViewers, broadcasters, staff, mods, vips, viewers)
    }

    private fun filterUserList(users: List<String>, part: String): List<String> {
        if (users.isEmpty()) {
            return users
        }

        if (part.isBlank()) {
            return users
        }

        return users.filter { it.contains(part, ignoreCase = true) }
    }

    fun setupFilter(
        delegate: ViewerListViewDelegate, presenter: ViewerListPresenter
    ) {
        presenter.directSubscribe(delegate.eventObserver(), DisposeOn.DESTROY) { event ->
            if (event is ViewDelegateEvent) {
                ClassCompat.invokeIf<IProxyEvent>(presenter) {
                    it.proxyEvent(event)
                }
            }
        }
    }
}