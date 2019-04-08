package com.bill.base

import android.content.Context
import android.util.AttributeSet
import com.bill.consumption.NewAddConsumptionBroadcastReceiveListener
import com.bill.consumption.OnNewAddConsumptionBroadcastReceiveListener
import com.common.base.BaseRelativeLayout
/**
 * Created by E on 2018/3/14.
 */
open class BaseBillView : BaseRelativeLayout{

    private val newAddConsumptionBroadcastReceiveListener = NewAddConsumptionBroadcastReceiveListener()

    constructor(context: Context) : super(context)

    constructor(context: Context , attrs : AttributeSet) : super(context , attrs)

    override fun init() {
        super.init()
        newAddConsumptionBroadcastReceiveListener.onNewAddConsumptionBroadcastReceiveListener = object : OnNewAddConsumptionBroadcastReceiveListener(){
            override fun onNewAddConsumption() {
                onSummaryRefresh()
            }
        }
    }

    open fun onSummaryRefresh(){

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        newAddConsumptionBroadcastReceiveListener?.register()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        newAddConsumptionBroadcastReceiveListener.unRegister()
    }

}