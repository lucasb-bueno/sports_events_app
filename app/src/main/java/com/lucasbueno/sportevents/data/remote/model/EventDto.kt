package com.lucasbueno.sportevents.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lucasbueno.sportevents.domain.model.Event

data class EventDto(
    @SerializedName("i")
    val eventId: String,

    @SerializedName("si")
    val sportId: String?,

    @SerializedName("d")
    val eventName: String?,

    @SerializedName("tt")
    val eventTimestamp: Long?
)

fun EventDto.toEvent() = Event (
    eventId = this.eventId,
    sportId = this.sportId,
    eventName = this.eventName,
    eventTimestamp = this.eventTimestamp
)

