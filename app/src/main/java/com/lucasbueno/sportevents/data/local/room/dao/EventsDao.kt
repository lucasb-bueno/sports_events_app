package com.lucasbueno.sportevents.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucasbueno.sportevents.data.local.room.model.EventEntity

@Dao
interface EventsDao {
    // Fetch all events for a specific sport
    @Query("SELECT * FROM event_table WHERE sportId = :sportId AND eventId = :eventId LIMIT 1")
    suspend fun getAllEventsForSport(sportId: Long, eventId: String): EventEntity

    @Query("SELECT * FROM event_table WHERE isFavorite = 1")
    suspend fun getFavoriteEvents(): List<EventEntity>

    // Update favorite status of an event
    @Query("UPDATE event_table SET isFavorite = :isFavorite WHERE eventId = :eventId")
    suspend fun updateEventFavoriteStatus(eventId: String, isFavorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEventFavorites(eventEntity: EventEntity)
}
