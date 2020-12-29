package com.example.mocktestapp.home

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.mocktestapp.common.BaseViewModel
import com.example.mocktestapp.common.utils.whenFail
import com.example.mocktestapp.common.utils.whenSuccess
import com.example.mocktestapp.repository.Repo
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class HomeViewModel(private val repo: Repo) : BaseViewModel<HomeViewState>() {
    override val _viewState = MutableLiveData<HomeViewState>().apply { HomeViewState() }

    private val handler = Handler(Looper.getMainLooper())
    private val rsrpValues: ArrayList<Entry> = arrayListOf()
    val rsrpLiveData: MutableLiveData<LineData> = MutableLiveData()
    private val rsrqValues: ArrayList<Entry> = arrayListOf()
    val rsrqLiveData: MutableLiveData<LineData> = MutableLiveData()
    private val snrValues: ArrayList<Entry> = arrayListOf()
    val snrLiveData: MutableLiveData<LineData> = MutableLiveData()

    fun getReading() = launch {
        repo.getReadings().whenSuccess {
            setState { HomeViewState(data = it) }
        }.whenFail {
        }
    }

    init {
        handler.postDelayed(object : Runnable {
            override fun run() {
                getReading()
                handler.postDelayed(this, 2000)
            }
        }, 0)
    }

    fun addToRsrpChart(value: Int) {
        rsrpValues.add(Entry(getTime().toFloat(), value.toFloat()))
        if (rsrpValues.size > 20){
            rsrpValues.removeAt(0)
        }
        val rsrpSet = LineDataSet(rsrpValues, "RSRP")
        rsrpSet.fillAlpha = 110
        rsrpSet.notifyDataSetChanged()
        val dataSets: ArrayList<ILineDataSet> = arrayListOf()
        dataSets.add(rsrpSet)
        rsrpLiveData.postValue(LineData(dataSets))
    }

    fun addToRsrqChart(value: Int) {
        rsrqValues.add(Entry(getTime().toFloat(), value.toFloat()))
        if (rsrqValues.size > 20){
            rsrqValues.removeAt(0)
        }
        val rsrqSet = LineDataSet(rsrqValues, "RSRQ")
        rsrqSet.fillAlpha = 110
        rsrqSet.notifyDataSetChanged()
        val dataSets: ArrayList<ILineDataSet> = arrayListOf()
        dataSets.add(rsrqSet)
        rsrqLiveData.postValue(LineData(dataSets))
    }

    fun addToSnrChart(value: Int) {
        snrValues.add(Entry(getTime().toFloat(), value.toFloat()))
        if (snrValues.size > 20){
            snrValues.removeAt(0)
        }
        val snrSet = LineDataSet(snrValues, "SNR")
        snrSet.fillAlpha = 110
        snrSet.notifyDataSetChanged()
        val dataSets: ArrayList<ILineDataSet> = arrayListOf()
        dataSets.add(snrSet)
        snrLiveData.postValue(LineData(dataSets))
    }

    private fun getTime(): String {
        return DateTimeFormatter
                .ofPattern("HH.mmss")
                .withZone(ZoneId.of("CAT"))
                .format(Instant.now())
    }

    private fun setState(
            block: HomeViewState.() -> HomeViewState
    ) {
        setState(HomeViewState(), block)
    }
}