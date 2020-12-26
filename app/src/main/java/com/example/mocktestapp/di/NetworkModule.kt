package com.example.mocktestapp.di

import com.example.mocktestapp.common.Constants
import com.example.mocktestapp.network.ApiService
import com.example.mocktestapp.network.createOkHttpClient
import com.example.mocktestapp.network.createService
import com.example.mocktestapp.network.moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single<Retrofit.Builder> {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
    }

    factory { createOkHttpClient() }
    single { moshi() }

    single {
        createService<ApiService>(Constants.BASE_URL, get())
    }
}