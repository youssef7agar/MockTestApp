package com.example.mocktestapp.home

import com.example.mocktestapp.network.model.Reading
import java.lang.Exception

data class HomeViewState(
        val loading: Boolean = false,
        val error: Exception? = null,
        val data: Reading? = null
)