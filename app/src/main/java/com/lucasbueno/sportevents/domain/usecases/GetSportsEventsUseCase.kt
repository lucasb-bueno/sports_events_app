package com.lucasbueno.sportevents.domain.usecases

import com.lucasbueno.sportevents.domain.model.Sport
import com.lucasbueno.sportevents.domain.repository.MainRepository
import javax.inject.Inject

class GetSportsEventsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): List<Sport?> = repository.getSportsEvents()
}