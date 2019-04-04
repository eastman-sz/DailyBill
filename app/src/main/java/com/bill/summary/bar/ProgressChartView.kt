package com.bill.summary.bar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.bill.chart.ChartView
import com.sz.kk.daily.bill.R

class ProgressChartView : ChartView {

    private var mWidth = 0
    private var mHeight = 0

    private val paint = Paint()

    private var maxProgress = 0f
    private var currentProgress = 0f

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        mWidth = measuredWidth
        mHeight = measuredHeight
    }

    private fun initBasic(){
        paint.strokeWidth = 3f
        paint.isAntiAlias = true
    }

    override fun render(canvas: Canvas) {
        initBasic()

        drawProgress(canvas)
    }

    private fun drawProgress(canvas: Canvas){
        paint.color = ContextCompat.getColor(context , R.color.c15)
        val rect = RectF()
        rect.left = 0F
        rect.right = mWidth.toFloat()
        rect.top = 0F
        rect.bottom = mHeight.toFloat()
        canvas.drawRoundRect(rect , 8F , 8F , paint)

        if (0F == maxProgress || 0F == currentProgress){
            return
        }
        val percent = currentProgress/maxProgress
        val pWidth = percent*mWidth

        paint.color = getProgressColor(percent)

        val pRect = RectF()
        pRect.left = 0F
        pRect.right = pWidth
        pRect.top = 0F
        pRect.bottom = mHeight.toFloat()
        canvas.drawRoundRect(pRect , 8F , 8F , paint)
    }

    private fun getProgressColor(percent : Float) : Int{
        if (percent > 0.9F){
            return ContextCompat.getColor(context , R.color.c51)
        }else if (percent > 0.8F){
            return ContextCompat.getColor(context , R.color.c20)
        }else if (percent > 0.7F){
            return ContextCompat.getColor(context , R.color.c38)
        }else if (percent > 0.6F){
            return ContextCompat.getColor(context , R.color.c36)
        }else if (percent > 0.5F){
            return ContextCompat.getColor(context , R.color.c29)
        }
        return ContextCompat.getColor(context , R.color.c27)
    }


    fun setProgress(maxProgress : Float , currentProgress : Float){
        this.maxProgress = maxProgress
        this.currentProgress = currentProgress
        refreshChart()
    }



}