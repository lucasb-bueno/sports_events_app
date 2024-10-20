package com.lucasbueno.sportevents.domain.model

data class Event(
    val eventId: String,
    val sportId: String?,
    val eventName: String?,
    val eventTimestamp: Long?,
    val isFavorite: Boolean = false
)
