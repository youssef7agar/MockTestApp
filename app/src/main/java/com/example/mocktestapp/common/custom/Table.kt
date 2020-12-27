package com.example.mocktestapp.common.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mocktestapp.R

class Table(context: Context,
            attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {


    private val tvRSRP: TextView
    private val pbRSRP: ProgressBar
    private val tvRSRQ: TextView
    private val pbRSRQ: ProgressBar
    private val tvSNR: TextView
    private val pbSNR: ProgressBar

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.table_view, this, true)

        tvRSRP = findViewById(R.id.tv_rsrp_value)
        pbRSRP = findViewById(R.id.rsrp_pb)
        tvRSRQ = findViewById(R.id.tv_rsrq_value)
        pbRSRQ = findViewById(R.id.rsrq_pb)
        tvSNR = findViewById(R.id.tv_snr_value)
        pbSNR = findViewById(R.id.snr_pb)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.Table, 0, 0)

            val rsrpValue = resources.getInteger(typedArray
                    .getResourceId(R.styleable.Table_rsrp_value, R.string.rsrp))
            tvRSRP.text = rsrpValue.toString()
            pbRSRP.progress = (rsrpValue + 140) * (100 / 90)

            val rsrqValue = resources.getInteger(typedArray
                    .getResourceId(R.styleable.Table_rsrq_value, R.string.rsrq))
            tvRSRQ.text = rsrqValue.toString()
            pbRSRQ.progress = (rsrqValue + 25) * (4)

            val snrValue = resources.getInteger(typedArray
                    .getResourceId(R.styleable.Table_snr_value, R.string.snr))
            tvSNR.text = snrValue.toString()
            pbSNR.progress = (snrValue + 5) * (100 / 40)

            typedArray.recycle()
        }
    }

    fun setRsrpValue(value: Int){
        tvRSRP.text = value.toString()
        pbRSRP.progress = (value + 140) * (100 / 90)
    }

    fun setRsrqValue(value : Int){
        tvRSRQ.text = value.toString()
        pbRSRQ.progress = (value + 25) * (4)
    }

    fun setSnrValue(value: Int){
        tvSNR.text = value.toString()
        pbSNR.progress = (value + 5) * (100 / 40)
    }
}