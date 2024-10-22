package com.lucasbueno.sportevents.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lucasbueno.sportevents.data.remote.datasource.SportsDataRemoteDataSourceImpl
import com.lucasbueno.sportevents.data.local.datasource.SportsEventsLocalDataSourceImpl
import com.lucasbueno.sportevents.data.repository.MainRepositoryImpl
import com.lucasbueno.sportevents.data.local.room.dao.EventsDao
import com.lucasbueno.sportevents.data.local.room.dao.SportsDao
import com.lucasbueno.sportevents.data.local.room.SportsEventsAppDatabase
import com.lucasbueno.sportevents.data.service.SportsService
import com.lucasbueno.sportevents.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://ios-kaizen.github.io/MockSports/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideSportsService(retrofit: Retrofit): SportsService =
        retrofit.create(SportsService::class.java)

    @Provides
    @Singleton
    fun provideSportsRepository(
        remoteDataSource: SportsDataRemoteDataSourceImpl,
        localDataSource: SportsEventsLocalDataSourceImpl
    ): MainRepository {
        return MainRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideSportsRemoteDataSource(service: SportsService): SportsDataRemoteDataSourceImpl =
        SportsDataRemoteDataSourceImpl(service)

    @Provides
    @Singleton
    fun provideDatabase(
        context: Context
    ): SportsEventsAppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SportsEventsAppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSportDao(database: SportsEventsAppDatabase): SportsDao {
        return database.sportDao()
    }

    @Provides
    @Singleton
    fun provideEventDao(database: SportsEventsAppDatabase): EventsDao {
        return database.eventDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        sportsDao: SportsDao,
        eventsDao: EventsDao
    ): SportsEventsLocalDataSourceImpl = SportsEventsLocalDataSourceImpl(sportsDao, eventsDao)
}