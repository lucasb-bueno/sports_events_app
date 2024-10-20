package com.lucasbueno.sportevents.domain.usecases

import com.lucasbueno.sportevents.domain.repository.MainRepository
import javax.inject.Inject

class UpdateFavoriteSportsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(sportId: String, isFavorite: Boolean) {
        return repository.updateSportIsFavorite(sportId, isFavorite)
    }
}