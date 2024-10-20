package com.lucasbueno.sportevents.data.remote.datasource

import com.lucasbueno.sportevents.data.remote.model.SportDto
import com.lucasbueno.sportevents.data.service.SportsService
import javax.inject.Inject

class SportsDataRemoteDataSourceImpl @Inject constructor(
    private val service: SportsService
) {
    suspend fun getData(): List<SportDto?> = service.getData()
}