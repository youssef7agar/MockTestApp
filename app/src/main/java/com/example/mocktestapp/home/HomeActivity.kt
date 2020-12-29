package com.example.mocktestapp.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mocktestapp.R
import com.example.mocktestapp.R.integer.*
import com.example.mocktestapp.common.custom.Table
import com.github.mikephil.charting.charts.LineChart
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var rsrpChart: LineChart
    private lateinit var rsrqChart: LineChart
    private lateinit var snrChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val table = findViewById<Table>(R.id.table)
        rsrpChart = findViewById(R.id.rsrp_chart)
        rsrqChart = findViewById(R.id.rsrq_chart)
        snrChart = findViewById(R.id.snr_chart)
        setRsrpChart()
        setRsrqChart()
        setSnrChart()

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                viewModel.getReading()
            }
        }, 2000, 2000)

        viewModel.viewState.observe(this, {
            it.let { homeViewState ->
                table.setRsrpValue(homeViewState.data?.RSRP ?: rsrp_value)
                viewModel.addToRsrpChart(homeViewState.data?.RSRP ?: rsrp_value)
                table.setRsrqValue(homeViewState.data?.RSRQ ?: rsrq_value)
                viewModel.addToRsrqChart(homeViewState.data?.RSRQ ?: rsrq_value)
                table.setSnrValue(homeViewState.data?.SINR ?: snr_value)
                viewModel.addToSnrChart(homeViewState.data?.SINR ?: snr_value)
            }
        })

        viewModel.rsrpLiveData.observe(this, {
            rsrpChart.data = it
            rsrpChart.invalidate()
        })
        viewModel.rsrqLiveData.observe(this, {
            rsrqChart.data = it
            rsrqChart.invalidate()
        })
        viewModel.snrLiveData.observe(this, {
            snrChart.data = it
            snrChart.invalidate()
        })
    }

    private fun setRsrpChart() {
        rsrpChart.isDragEnabled = true
        rsrpChart.setScaleEnabled(true)
        rsrpChart.setTouchEnabled(true)
    }

    private fun setRsrqChart() {
        rsrqChart.isDragEnabled = true
        rsrqChart.setScaleEnabled(true)
        rsrqChart.setTouchEnabled(true)
    }

    private fun setSnrChart() {
        snrChart.isDragEnabled = true
        snrChart.setScaleEnabled(true)
        snrChart.setTouchEnabled(true)
    }

}