package com.bill.wheelview

import android.content.Context
import android.view.View
import com.bill.consumption.type.BigType
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.wheelview.adapter.BaseWheelAdapter

class BigTypeWheelViewAdapter : BaseWheelAdapter<BigType> {

    constructor(context: Context , list: List<BigType>) : super(context, list, R.layout.bigtype_wheelview_adapter)

    override fun getConvertView(view: View, p1: List<BigType>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(view , R.id.textView)

        val bigType =  list[position]

        textView.text = bigType.typeName
    }

}