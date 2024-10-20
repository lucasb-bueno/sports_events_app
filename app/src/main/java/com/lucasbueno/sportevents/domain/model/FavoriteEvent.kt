package com.lucasbueno.sportevents.domain.model

data class FavoriteEvent(
    val sportId: String,
    val eventId: String,
    val isFavorite: Boolean
)
