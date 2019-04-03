package com.bill.summary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.bill.BillList
import com.bill.bill.DailyBill
import com.bill.bill.DailyBillDbHelper
import com.common.base.BaseRelativeLayout
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import kotlinx.android.synthetic.main.lastest_seven_day_layoutview.view.*

class Latest7DayChartView : BaseRelativeLayout{

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context , attrs : AttributeSet) :super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.lastest_seven_day_layoutview , this)


    }

    fun setPercent(percent : Float){
        waveView.setBaseLine(percent)
    }

    private fun showBillList(billList: ArrayList<DailyBill>){
        lastest7DayChartViewss.showBillList(billList)
    }

    fun freshData(){
        val cTime = System.currentTimeMillis()
        val startTimeStamp = DateHepler.getWeekStartTimestamp(cTime)
        val endTimeStamp = DateHepler.getWeekEndTimestamp(cTime)

        val bills = DailyBillDbHelper.getDailyBills(startTimeStamp , endTimeStamp)
        showBillList(bills)
    }

}