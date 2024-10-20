package com.lucasbueno.sportevents.data.service

import com.lucasbueno.sportevents.data.remote.model.SportDto
import retrofit2.http.GET

interface SportsService {
    @GET("sports.json")
    suspend fun getData(): List<SportDto?>
}