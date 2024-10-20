package com.lucasbueno.sportevents.domain.usecases

import com.lucasbueno.sportevents.domain.repository.MainRepository
import javax.inject.Inject

class UpdateFavoriteEventsUseCase@Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(sportId: String, eventId: String, isFavorite: Boolean) {
        return repository.updateFavoriteEvents(sportId, eventId = eventId, isFavorite)
    }
}