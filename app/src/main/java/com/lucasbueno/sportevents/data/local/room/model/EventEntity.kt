package com.lucasbueno.sportevents.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lucasbueno.sportevents.domain.model.FavoriteEvent

@Entity(tableName = "event_table")
data class EventEntity(
    @PrimaryKey val eventId: String,
    val sportId: String,
    val isFavorite: Boolean
)

fun EventEntity.toFavoriteEvent() = FavoriteEvent(
    sportId = this.sportId,
    eventId = this.eventId,
    isFavorite = this.isFavorite
)
