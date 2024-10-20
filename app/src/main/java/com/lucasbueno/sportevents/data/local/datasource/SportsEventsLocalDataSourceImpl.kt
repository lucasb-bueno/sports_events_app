package com.lucasbueno.sportevents.data.local.datasource

import com.lucasbueno.sportevents.data.local.room.model.EventEntity
import com.lucasbueno.sportevents.data.local.room.dao.EventsDao
import com.lucasbueno.sportevents.data.local.room.model.SportEntity
import com.lucasbueno.sportevents.data.local.room.dao.SportsDao
import javax.inject.Inject

class SportsEventsLocalDataSourceImpl @Inject constructor(
    private val sportDao: SportsDao,
    private val eventsDao: EventsDao
) {
    // Sports

    suspend fun getAllFavoriteSports(): List<SportEntity> {
        return sportDao.getAllFavoriteSports()
    }

    suspend fun insertSport(sportId: String, isFavorite: Boolean) {
        val sport = SportEntity(sportId = sportId, isFavorite = isFavorite)
        sportDao.insertSport(sport)
    }


    // Events

    suspend fun insertEventFavorites(sportId: String, eventId: String, isFavorite: Boolean) {
        val event = EventEntity(sportId = sportId, eventId = eventId, isFavorite = isFavorite)
        eventsDao.insertEventFavorites(event)
    }

    suspend fun getAllFavoriteEvents(): List<EventEntity> {
        return eventsDao.getFavoriteEvents()
    }
}