package com.example.mocktestapp.common.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mocktestapp.R

class Table(context: Context,
            attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {


    private val tvRSRP: TextView
    private val pbRSRP: View
    private val tvRSRQ: TextView
    private val pbRSRQ: View
    private val tvSNR: TextView
    private val pbSNR: View

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.table_view, this, true)

        tvRSRP = findViewById(R.id.tv_rsrp_value)
        pbRSRP = findViewById(R.id.rsrp_pb)
        tvRSRQ = findViewById(R.id.tv_rsrq_value)
        pbRSRQ = findViewById(R.id.rsrq_pb)
        tvSNR = findViewById(R.id.tv_snr_value)
        pbSNR = findViewById(R.id.snr_pb)
    }

    fun setRsrpValue(value: Int) {
        tvRSRP.text = value.toString()
        val progress = (value + 140) * (100 / 90)
        val params = pbRSRP.layoutParams as LayoutParams
        if (progress > 0) {
            params.width = dpToPixel(progress)
        } else {
            params.width = 1
        }
        params.topToBottom = R.id.tv_rsrp_headline
        params.startToStart = R.id.limit1
        params.bottomToBottom = R.id.table_parent
        when {
            value <= -110 -> {
                pbRSRP.setBackgroundColor(resources.getColor(R.color.rsrp1, context.theme))
            }
            value in -110..-100 -> {
                pbRSRP.setBackgroundColor(resources.getColor(R.color.rsrp2, context.theme))
            }
            value in -100..-90 -> {
                pbRSRP.setBackgroundColor(resources.getColor(R.color.rsrp3, context.theme))
            }
            value in -90..-80 -> {
                pbRSRP.setBackgroundColor(resources.getColor(R.color.rsrp4, context.theme))
            }
            value in -80..-70 -> {
                pbRSRP.setBackgroundColor(resources.getColor(R.color.rsrp5, context.theme))
            }
            value in -70..-60 -> {
                pbRSRP.setBackgroundColor(resources.getColor(R.color.rsrp6, context.theme))
            }
            else -> {
                pbRSRP.setBackgroundColor(resources.getColor(R.color.rsrp7, context.theme))
            }
        }
    }

    fun setRsrqValue(value: Int) {
        tvRSRQ.text = value.toString()
        val progress = (value + 25) * (4)
        val params = pbRSRQ.layoutParams as LayoutParams
        if (progress > 0) {
            params.width = dpToPixel(progress)
        } else {
            params.width = 1
        }
        params.topToBottom = R.id.tv_rsrq_headline
        params.startToStart = R.id.limit2
        params.bottomToBottom = R.id.table_parent

        if (value <= -19.5) {
            pbRSRQ.setBackgroundColor(resources.getColor(R.color.rsrq1, context.theme))
        } else if (-19.5 < value && value <= -14) {
            pbRSRQ.setBackgroundColor(resources.getColor(R.color.rsrq2, context.theme))
        } else if (value in -14..-9) {
            pbRSRQ.setBackgroundColor(resources.getColor(R.color.rsrq3, context.theme))
        } else if (value in -9..-3) {
            pbRSRQ.setBackgroundColor(resources.getColor(R.color.rsrq4, context.theme))
        } else {
            pbRSRQ.setBackgroundColor(resources.getColor(R.color.rsrq5, context.theme))
        }
    }

    fun setSnrValue(value: Int) {
        tvSNR.text = value.toString()
        val progress = (value + 5) * (100 / 40)
        val params = pbSNR.layoutParams as LayoutParams
        if (progress > 0) {
            params.width = dpToPixel(progress)
        } else {
            params.width = 1
        }
        params.topToBottom = R.id.tv_snr_headline
        params.startToStart = R.id.limit3
        params.bottomToBottom = R.id.table_parent

        when {
            value <= 0 -> {
                pbSNR.setBackgroundColor(resources.getColor(R.color.snr1, context.theme))
            }
            value in 1..5 -> {
                pbSNR.setBackgroundColor(resources.getColor(R.color.snr2, context.theme))
            }
            value in 6..10 -> {
                pbSNR.setBackgroundColor(resources.getColor(R.color.snr3, context.theme))
            }
            value in 11..15 -> {
                pbSNR.setBackgroundColor(resources.getColor(R.color.snr4, context.theme))
            }
            value in 16..20 -> {
                pbSNR.setBackgroundColor(resources.getColor(R.color.snr5, context.theme))
            }
            value in 21..25 -> {
                pbSNR.setBackgroundColor(resources.getColor(R.color.snr6, context.theme))
            }
            value in 26..30 -> {
                pbSNR.setBackgroundColor(resources.getColor(R.color.snr7, context.theme))
            }
            else -> {
                pbSNR.setBackgroundColor(resources.getColor(R.color.snr8, context.theme))
            }
        }
    }

    fun dpToPixel(value: Int): Int {
        val scale = context.resources.displayMetrics.density
        val pixels = (value * scale + 0.5f)
        return pixels.toInt()
    }
}