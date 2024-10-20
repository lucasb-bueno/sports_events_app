package com.lucasbueno.sportevents.domain.usecases

import com.lucasbueno.sportevents.domain.model.FavoriteSport
import com.lucasbueno.sportevents.domain.repository.MainRepository
import javax.inject.Inject

class GetFavoriteSportsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<FavoriteSport> {
        return mainRepository.getFavoriteSports()
    }
}