package com.example.mocktestapp.home

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mocktestapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val rsrp = findViewById<TextView>(R.id.tv_rsrp)
        val rsrq = findViewById<TextView>(R.id.tv_rsrq)
        val snr = findViewById<TextView>(R.id.tv_snr)


        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                viewModel.getReading()
            }
        }, 2000, 2000)

        viewModel.viewState.observe(this, Observer {
            it.let { homeViewState ->
                rsrp.text = homeViewState.data?.RSRP.toString()
                rsrq.text = homeViewState.data?.RSRQ.toString()
                snr.text = homeViewState.data?.SINR.toString()
            }
        })
    }
}