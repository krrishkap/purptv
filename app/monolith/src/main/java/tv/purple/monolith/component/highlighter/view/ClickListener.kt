package tv.purple.monolith.component.highlighter.view

import tv.purple.monolith.component.highlighter.data.model.KeywordData
import tv.purple.monolith.core.db.Entity

interface ClickListener {
    fun onChangeColorClicked(item: Entity<KeywordData>)
    fun onVibrationClicked(item: Entity<KeywordData>)
    fun onChangeTypeClicked(item: Entity<KeywordData>)
}