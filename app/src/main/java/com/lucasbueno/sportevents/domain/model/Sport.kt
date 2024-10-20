package com.lucasbueno.sportevents.domain.model

data class Sport(
    val sportId: String?,
    val sportName: String?,
    val activeEvents: List<Event>?
)
