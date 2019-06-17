package com.bill.wheelview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.consumption.martket.Market
import com.bill.consumption.martket.MarketDbHelper
import com.bill.consumption.martket.OnMarketChangeBroadcastReceiver
import com.bill.consumption.martket.OnMarketChangeBroadcastReceiverListener
import com.common.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.natureinfo_wheelview.view.*

class MarketWheelView : BaseKotlinRelativeLayout {

    private val onMarketChangeBroadcastReceiver = OnMarketChangeBroadcastReceiver()

    private val list = ArrayList<Market>()

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.market_wheelview , this)
    }

    override fun initListener() {
        onMarketChangeBroadcastReceiver.onMarketChangeBroadcastReceiverListener = object : OnMarketChangeBroadcastReceiverListener(){
            override fun onMarketChanged() {
                showAllMarkets()
            }
        }
    }

    fun showAllMarkets(){
        list.clear()
        list.addAll(MarketDbHelper.getMarkets())

        val adapter = MarketWheelViewAdapter(context, list)
        wheelView.viewAdapter = adapter
        wheelView.visibleItems = 5
        wheelView.setCurrentItem(1 , false)
    }

    fun getMarket() : Market {
        val curPosition = wheelView.currentItem
        return list[curPosition]
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        onMarketChangeBroadcastReceiver?.register()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        onMarketChangeBroadcastReceiver?.unRegister()
    }

}