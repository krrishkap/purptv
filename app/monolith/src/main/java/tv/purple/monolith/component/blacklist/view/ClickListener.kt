package tv.purple.monolith.component.blacklist.view

import tv.purple.monolith.component.blacklist.data.model.KeywordData
import tv.purple.monolith.core.db.Entity

interface ClickListener {
    fun onChangeTypeClicked(item: Entity<KeywordData>)
}