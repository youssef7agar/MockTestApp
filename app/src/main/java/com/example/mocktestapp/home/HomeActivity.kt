package com.example.mocktestapp.home

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mocktestapp.R
import com.example.mocktestapp.R.integer.*
import com.example.mocktestapp.common.custom.Table
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val table = findViewById<Table>(R.id.table)


        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                viewModel.getReading()
            }
        }, 2000, 2000)

        viewModel.viewState.observe(this, Observer {
            it.let { homeViewState ->
                table.setRsrpValue(homeViewState.data?.RSRP ?: rsrp_value)
                table.setRsrqValue(homeViewState.data?.RSRQ ?: rsrq_value)
                table.setSnrValue(homeViewState.data?.SINR ?: snr_value)
            }
        })
    }
}