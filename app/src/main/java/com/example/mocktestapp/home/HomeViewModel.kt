package com.example.mocktestapp.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mocktestapp.common.BaseViewModel
import com.example.mocktestapp.common.utils.whenFail
import com.example.mocktestapp.common.utils.whenSuccess
import com.example.mocktestapp.repository.Repo
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: Repo) : BaseViewModel<HomeViewState>() {
    override val _viewState = MutableLiveData<HomeViewState>().apply { HomeViewState() }

    fun getReading() = launch {
        Log.d("XXXXXXXXXXXXXXX", "getReading: HEREEEEE")
        repo.getReadings().whenSuccess {
            Log.d("XXXXXXXXXXXXXXXXX", "getReading: $it")
            setState { HomeViewState(data = it) }
        }.whenFail {
            Log.d("XXXXXXXXXXXXXXXXXX", "getReading: $it")
        }
    }

    private fun setState(
        block: HomeViewState.() -> HomeViewState
    ) {
        setState(HomeViewState(), block)
    }
}