package com.lucasbueno.sportevents.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lucasbueno.sportevents.domain.model.Sport

data class SportDto(
    @SerializedName("i")
    val sportId: String?,

    @SerializedName("d")
    val sportName: String?,

    @SerializedName("e")
    val activeEvents: List<EventDto>?
)

fun SportDto.toSport() = Sport (
    sportId = this.sportId,
    sportName = this.sportName,
    activeEvents = this.activeEvents?.map { it.toEvent() }
)

