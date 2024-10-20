package com.lucasbueno.sportevents.data.repository

import com.lucasbueno.sportevents.data.remote.datasource.SportsDataRemoteDataSourceImpl
import com.lucasbueno.sportevents.data.local.datasource.SportsEventsLocalDataSourceImpl
import com.lucasbueno.sportevents.data.remote.model.toSport
import com.lucasbueno.sportevents.data.local.room.model.toFavoriteEvent
import com.lucasbueno.sportevents.data.local.room.model.toFavoriteSport
import com.lucasbueno.sportevents.domain.model.FavoriteEvent
import com.lucasbueno.sportevents.domain.model.FavoriteSport
import com.lucasbueno.sportevents.domain.model.Sport
import com.lucasbueno.sportevents.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: SportsDataRemoteDataSourceImpl,
    private val localDataSource: SportsEventsLocalDataSourceImpl
) : MainRepository {

    override suspend fun getSportsEvents(): List<Sport?> {
        return remoteDataSource.getData().map { it?.toSport() }
    }

    override suspend fun updateSportIsFavorite(sportId: String, isFavorite: Boolean) {
        localDataSource.insertSport(sportId, isFavorite)
    }

    override suspend fun getFavoriteSports(): List<FavoriteSport> {
        return localDataSource.getAllFavoriteSports().map { it.toFavoriteSport() }
    }

    override suspend fun updateFavoriteEvents(
        sportId: String,
        eventId: String,
        isFavorite: Boolean
    ) {
        localDataSource.insertEventFavorites(
            sportId = sportId,
            eventId = eventId,
            isFavorite = isFavorite
        )
    }

    override suspend fun getFavoriteEvents(): List<FavoriteEvent> {
        return localDataSource.getAllFavoriteEvents().map {
            it.toFavoriteEvent()
        }
    }
}