package com.example.mocktestapp.common.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class nProgressBar(context: Context,
                  attrs: AttributeSet? = null) : View(context, attrs) {
    private var color: Int = 0
    private var bar: Paint = Paint()
    private var rect: Rect = Rect()
    private var progress: Int = 0


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bar.color = color
        rect.left = 0
        rect.top = 0
        rect.right = rect.left + progress
        rect.bottom = 50

        canvas.drawRect(rect, bar)
    }
    fun set(progress: Int, color: String){
        this.color = Color.parseColor(color)
        this .progress = progress
    }
}