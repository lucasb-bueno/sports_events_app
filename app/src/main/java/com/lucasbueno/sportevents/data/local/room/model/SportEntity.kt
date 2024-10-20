package com.lucasbueno.sportevents.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lucasbueno.sportevents.domain.model.FavoriteSport

@Entity(tableName = "sport_table")
data class SportEntity(
    @PrimaryKey val sportId: String,
    var isFavorite: Boolean = false
)

fun SportEntity.toFavoriteSport() = FavoriteSport(
    sportId = this.sportId,
    isFavorite = this.isFavorite
)