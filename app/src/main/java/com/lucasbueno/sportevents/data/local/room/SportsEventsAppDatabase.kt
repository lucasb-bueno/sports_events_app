package com.lucasbueno.sportevents.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucasbueno.sportevents.data.local.room.dao.EventsDao
import com.lucasbueno.sportevents.data.local.room.dao.SportsDao
import com.lucasbueno.sportevents.data.local.room.model.EventEntity
import com.lucasbueno.sportevents.data.local.room.model.SportEntity

@Database(entities = [SportEntity::class, EventEntity::class], version = 2, exportSchema = false)
abstract class SportsEventsAppDatabase : RoomDatabase() {
    abstract fun sportDao(): SportsDao
    abstract fun eventDao(): EventsDao
}