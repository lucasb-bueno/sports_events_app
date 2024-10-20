package com.lucasbueno.sportevents.domain.repository

import com.lucasbueno.sportevents.domain.model.FavoriteEvent
import com.lucasbueno.sportevents.domain.model.FavoriteSport
import com.lucasbueno.sportevents.domain.model.Sport

interface MainRepository {
    suspend fun getSportsEvents(): List<Sport?>
    suspend fun updateSportIsFavorite(sportId: String, isFavorite: Boolean)
    suspend fun getFavoriteSports(): List<FavoriteSport>
    suspend fun updateFavoriteEvents(sportId: String, eventId: String, isFavorite: Boolean)
    suspend fun getFavoriteEvents(): List<FavoriteEvent>
}