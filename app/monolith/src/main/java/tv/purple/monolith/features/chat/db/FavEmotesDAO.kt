package tv.purple.monolith.features.chat.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import tv.purple.monolith.features.chat.db.entities.FavEmoteEntity

@Dao
interface FavEmotesDAO {
    @Query("SELECT * FROM emotes_v3")
    fun getAll(): Single<List<FavEmoteEntity>>

    @Query("SELECT * FROM emotes_v3 WHERE channelId = :channelId")
    fun getForChannel(channelId: String): Single<List<FavEmoteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(emote: FavEmoteEntity): Completable

    @Delete
    fun delete(emote: FavEmoteEntity): Completable

    @Query("DELETE FROM emotes_v3 WHERE packageSet = :type AND channelId = :channelId AND emoteCode = :code")
    fun delete(code: String, channelId: String, type: String): Completable

    @Query("DELETE FROM emotes_v3 WHERE packageSet = :type AND channelId = :channelId AND emoteId = :id")
    fun deleteById(id: String, channelId: String, type: String): Completable
}