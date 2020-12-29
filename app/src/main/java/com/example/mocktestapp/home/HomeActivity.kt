package com.example.mocktestapp.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mocktestapp.R
import com.example.mocktestapp.R.integer.*
import com.example.mocktestapp.common.custom.Table
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import org.koin.androidx.viewmodel.ext.android.viewModel


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
        rsrpChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        rsrpChart.axisRight.isEnabled = false
        rsrpChart.setDrawBorders(true)
        rsrpChart.setDrawMarkers(true)
        rsrpChart.isDragEnabled = true
        rsrpChart.setScaleEnabled(true)
        rsrpChart.setTouchEnabled(true)
    }

    private fun setRsrqChart() {
        rsrqChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        rsrqChart.axisRight.isEnabled = false
        rsrqChart.setDrawBorders(true)
        rsrqChart.setDrawMarkers(true)
        rsrqChart.isDragEnabled = true
        rsrqChart.setScaleEnabled(true)
        rsrqChart.setTouchEnabled(true)
    }

    private fun setSnrChart() {
        snrChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        snrChart.axisRight.isEnabled = false
        snrChart.setDrawBorders(true)
        snrChart.setDrawMarkers(true)
        snrChart.isDragEnabled = true
        snrChart.setScaleEnabled(true)
        snrChart.setTouchEnabled(true)
    }

}