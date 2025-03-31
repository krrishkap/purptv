package tv.purple.monolith.features.usersearch

import androidx.appcompat.widget.SearchView
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.twitch.android.shared.chat.viewerlist.ViewerListViewDelegate
import javax.inject.Inject

class ViewFactory @Inject constructor() {
    fun createSearchBar(delegate: ViewerListViewDelegate): SearchView {
        return delegate.contentView.getView(resName = RES_STRINGS.viewer_list_dialog__search)
    }
}