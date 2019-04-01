package com.bill.wheelview

import android.content.Context
import android.view.View
import com.common.base.CustomFontDigitTextView
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import com.wheelview.adapter.BaseWheelAdapter

class DayOfMonthWheelViewAdapter : BaseWheelAdapter<String> {

    private var year : String ?= null
    private var month : String ?= null

    constructor(context: Context , list: List<String> , year : String , month: String) : super(context, list, R.layout.day_of_month_wheelview_adapter){
        this.year = year
        this.month = month
    }

    override fun getConvertView(convertView: View, list: List<String>, position: Int) {
        val textView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.textView)
        val dayOfWeekTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.dayOfWeekTextView)

        val text = list[position]

        textView.text = text.plus("日")

        val date = year.plus("-$month").plus("-$text")

        val timeStamp = DateHepler.dateFormatString2Timestamp(date , "yyyy-MM-dd")
        val dayOfWeekText = DateHepler.getDayOfWeekString(timeStamp)

        dayOfWeekTextView.text = dayOfWeekText

    }
}