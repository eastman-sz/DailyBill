package com.bill.wheelview.barChart

import android.content.Context
import android.view.View
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.wheelview.adapter.BaseWheelAdapter

class BarChartTypeWheelViewAdapter : BaseWheelAdapter<BarChart> {

    constructor(context: Context , list: List<BarChart>) : super(context, list, R.layout.bar_chart_type_wheelview_adapter)

    override fun getConvertView(convertView: View, list: List<BarChart>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.textView)

        val barChart = list[position]

        textView.text = barChart.name
    }

}