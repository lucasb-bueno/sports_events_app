package com.lucasbueno.sportevents.domain.usecases

import com.lucasbueno.sportevents.domain.model.FavoriteEvent
import com.lucasbueno.sportevents.domain.repository.MainRepository
import javax.inject.Inject

class GetFavoriteEventsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<FavoriteEvent> {
        return mainRepository.getFavoriteEvents()
    }
}