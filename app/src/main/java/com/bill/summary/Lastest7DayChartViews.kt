package com.bill.summary

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.bill.bill.BillList
import com.bill.bill.DailyBill
import com.bill.chart.ChartView
import com.bill.util.ILog
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.MathUtil
import com.utils.lib.ss.info.DeviceInfo
import java.util.ArrayList

class Lastest7DayChartViews : ChartView {

    val paint = Paint()
    val list = ArrayList<DailyBill>()

    var mWidth = 0
    var mHeight = 0
    var xStart = 0
    var xLength = 0
    var xStep = 0f
    var yTopMargin = 0f
    var yLength = 0f

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) :super(context , attrs){
        init()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        mWidth = measuredWidth
        mHeight = measuredHeight

        refreshChart()
    }

    override fun init() {
        super.init()

        paint.color = resources.getColor(R.color.c1)

        initBasic()
    }

    private fun initBasic(){
        mWidth = measuredWidth
        mHeight = measuredHeight
        xStart = DeviceInfo.dip2px(context , 15f)
        xLength = mWidth - DeviceInfo.dip2px(context , 30f)
        xStep = MathUtil.divideF(xLength.toFloat() , 7f , 15)
        yTopMargin = xStart.toFloat()
        yLength = mHeight - yTopMargin

    }

    override fun render(canvas: Canvas?) {
        initBasic()
        drawLines(canvas)

    }

    private fun drawLines(canvas: Canvas?){
        var maxAmount = 0f

        list.forEach {
            if (it.amount > maxAmount){
                maxAmount = it.amount
            }
        }

        var xPoint  = xStart.toFloat()
        var lastY = mHeight.toFloat()

        list.forEach {
            val amount = it.amount
            xPoint += xStep
            val y = mHeight - yLength*MathUtil.divideF(amount , maxAmount , 15)

            canvas?.drawLine(xPoint - xStep , lastY , xPoint , y , paint)

            lastY = y

            ILog.e("xPoint: $xPoint  y: $y  mWidth: $mWidth  mHeight: $mHeight")
        }


    }

    fun showBillList(billList: List<DailyBill>){
        list.clear()
        list.addAll(billList)

        refreshChart()
    }



}