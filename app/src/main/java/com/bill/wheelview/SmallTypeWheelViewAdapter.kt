package com.bill.wheelview

import android.content.Context
import android.view.View
import com.bill.consumption.type.SmallType
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.sfs.adapter.BaseWheelAdapter
import com.sz.kk.daily.bill.R

class SmallTypeWheelViewAdapter : BaseWheelAdapter<SmallType> {

    constructor(context: Context , list: List<SmallType>) : super(context, list, R.layout.smalltype_wheelview_adapter)

    override fun getConvertView(view: View, p1: List<SmallType>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(view , R.id.textView)

        val smallType =  list[position]

        textView.text = smallType.typeName
    }

}