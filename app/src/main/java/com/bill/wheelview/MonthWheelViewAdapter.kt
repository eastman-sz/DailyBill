package com.bill.wheelview

import android.content.Context
import android.view.View
import com.common.base.CustomFontDigitTextView
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.wheelview.adapter.BaseWheelAdapter

class MonthWheelViewAdapter : BaseWheelAdapter<String> {

    constructor(context: Context , list: List<String>) : super(context, list, R.layout.wheelview_text_adapter_view)

    override fun getConvertView(convertView: View, list: List<String>, position: Int) {
        val textView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.textView)

        val text = list[position]

        textView.text = text.plus("月")
    }
}