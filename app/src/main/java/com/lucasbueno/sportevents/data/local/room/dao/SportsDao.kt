package com.lucasbueno.sportevents.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucasbueno.sportevents.data.local.room.model.SportEntity

@Dao
interface SportsDao {
    @Query("SELECT * FROM sport_table WHERE isFavorite = 1")
    suspend fun getAllFavoriteSports(): List<SportEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSport(sportId: SportEntity)
}
