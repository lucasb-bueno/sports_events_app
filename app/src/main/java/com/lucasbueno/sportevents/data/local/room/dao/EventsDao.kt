package com.lucasbueno.sportevents.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucasbueno.sportevents.data.local.room.model.EventEntity

@Dao
interface EventsDao {
    @Query("SELECT * FROM event_table WHERE isFavorite = 1")
    suspend fun getFavoriteEvents(): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEventFavorites(eventEntity: EventEntity)
}
