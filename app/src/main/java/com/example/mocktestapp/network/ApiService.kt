package com.example.mocktestapp.network

import com.example.mocktestapp.network.model.Reading
import retrofit2.http.GET

interface ApiService {
    @GET("random/")
    suspend fun getReadings(): Reading
}