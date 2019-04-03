package com.bill.daylist.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.martket.Market
import com.bill.dialog.DialogHelper
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.market_filter_view.view.*
/**
 * 商家选择。
 * @author E
 */
class MarketFilterView : BaseKotlinRelativeLayout{

    var marketId = 0

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.market_filter_view , this)
    }

    override fun initListener() {
        marketFilterLayout.setOnClickListener {
            DialogHelper.showMarketSelectDialog(context , object : OnCommonItemClickListener<Market>(){
                override fun onItemClick(it: Market) {
                    marketId = it.marketId
                    marketNameTextView.text = it.marketName

                }
            })
        }
    }




}