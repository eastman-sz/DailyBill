package com.bill.summary

import android.content.Context
import android.util.AttributeSet
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.NewAddConsumptionBroadcastReceiveListener
import com.bill.consumption.OnNewAddConsumptionBroadcastReceiveListener
import com.utils.lib.ss.common.DateHelper

open class BaseSummaryView : BaseKotlinRelativeLayout {

    private val newAddConsumptionBroadcastReceiveListener = NewAddConsumptionBroadcastReceiveListener()

    var startTimestamp = 0L
    var endTimestamp = 0L

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs)

    override fun init() {
        //今年
        val year = DateHelper.getYear()
        startTimestamp = DateHelper.getDayStartTimestamp(DateHelper.getFirstDayOfYear(year).time)
        endTimestamp = DateHelper.getDayEndTimestamp(DateHelper.getLastDayOfYear(year).time)

        super.init()
        newAddConsumptionBroadcastReceiveListener.onNewAddConsumptionBroadcastReceiveListener = object : OnNewAddConsumptionBroadcastReceiveListener(){
            override fun onNewAddConsumption() {
                onSummaryRefresh()
            }

            override fun onFilterTimeRangeChanged(newStartTimestamp: Long, newEndTimestamp: Long) {
                startTimestamp = newStartTimestamp
                endTimestamp = newEndTimestamp

                onSummaryRefresh()
            }
        }
    }

    open fun onSummaryRefresh(){

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        newAddConsumptionBroadcastReceiveListener.register()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        newAddConsumptionBroadcastReceiveListener.unRegister()

    }

}