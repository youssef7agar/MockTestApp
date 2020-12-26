package com.example.mocktestapp.repository

import com.example.mocktestapp.common.utils.Result
import com.example.mocktestapp.network.ApiService
import com.example.mocktestapp.network.model.Reading
import java.lang.Exception

class Repo(private val apiService: ApiService) {
    suspend fun getReadings(): Result<Reading> {
        return try {
            Result.Success(apiService.getReadings())
        }catch (e: Exception){
            Result.Error(e)
        }
    }
}