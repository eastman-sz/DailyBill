package com.bill.wheelview

import android.content.Context
import android.view.View
import com.bill.consumption.martket.Market
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.wheelview.adapter.BaseWheelAdapter

class MarketWheelViewAdapter : BaseWheelAdapter<Market> {

    constructor(context: Context , list: List<Market>) : super(context, list, R.layout.martket_wheelview_adapter)

    override fun getConvertView(convertView: View, list: List<Market>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.textView)

        val market = list[position]

        textView.text = market.marketName
    }

}