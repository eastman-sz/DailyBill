package com.bill.consumption.martket

import android.content.Context
import android.view.View
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
/**
 * Created by E on 2018/3/12.
 */
class MarketSelectAdapter : IBaseAdapter<Market> {

    constructor(context: Context , list : List<Market>) : super(context , list , R.layout.consuption_point_adapter_view)

    override fun getConvertView(conterView: View?, list : List<Market>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(conterView, R.id.textView)

        val market = list[position]
        val marketName = market.marketName

        textView.text = marketName
    }

}