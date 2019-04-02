package com.bill.summary

import android.content.Context
import android.view.View
import com.bill.base.BaseBillView
import com.bill.consumption.NewAddConsumptionBroadcastReceiveListener
import com.bill.consumption.OnNewAddConsumptionBroadcastReceiveListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.summary_view.view.*
/**
 * Created by E on 2018/3/12.
 */
class SummaryView : BaseBillView{

    private val newAddConsumptionBroadcastReceiveListener = NewAddConsumptionBroadcastReceiveListener()

    constructor(context: Context) : super(context){
        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("简报")
        commonTitleView.setLeftBtnVisibility(View.INVISIBLE)
    }

    override fun initViews() {
        View.inflate(context , R.layout.summary_view , this)
    }

    override fun initListener() {
        newAddConsumptionBroadcastReceiveListener.onNewAddConsumptionBroadcastReceiveListener = object : OnNewAddConsumptionBroadcastReceiveListener(){
            override fun onNewAddConsumption() {
                freshData()
            }
        }
    }

    override fun freshByHand(forceUpdate: Boolean) {
        freshData()
    }

    fun freshData(){
        totalSummaryView.getPeriodSummary()
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