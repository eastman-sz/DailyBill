package com.bill.summary.pie

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.bill.chart.ChartView
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.info.DeviceInfo
/**
 * 画饼状图。
 * @author E
 */
class PieChartView : ChartView{

    private var mWidth = 0
    private var mHeight = 0

    private val paint = Paint()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        mWidth = measuredWidth
        mHeight = measuredHeight

        refreshChart()
    }

    private fun initBasic(){
        paint.strokeWidth = 3f
        paint.isAntiAlias = true
    }

    override fun render(canvas: Canvas) {
        initBasic()
        drawPie(canvas)
    }

    private fun drawPie(canvas: Canvas){
        val leftMargin = DeviceInfo.dip2px(context , 25f).toFloat()
        val rightMargin = DeviceInfo.dip2px(context , 100f).toFloat()
        val length = (mWidth - leftMargin - rightMargin)

        val rectF = RectF()
        rectF.left = leftMargin
        rectF.right = length
        rectF.top = leftMargin
        rectF.bottom = length

        paint.color = ContextCompat.getColor(context , R.color.c1)

        canvas.drawArc(rectF , 0f , 20f , true , paint)

        paint.color = ContextCompat.getColor(context , R.color.c27)
        canvas.drawArc(rectF , 20f , 80f , true , paint)

        paint.color = ContextCompat.getColor(context , R.color.c29)
        canvas.drawArc(rectF , 100f , 260f , true , paint)

    }


}