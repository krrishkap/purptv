package tv.purple.monolith.features.chat.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotes_v3")
data class FavEmoteEntity(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    val emoteId: String?,
    val emoteCode: String,
    val channelId: String,
    val packageSet: String,
    val isAnimated: Boolean
)