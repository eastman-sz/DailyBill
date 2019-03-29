package com.bill.wheelview

import android.content.Context
import android.view.View
import com.bill.consumption.nature.NatureInfo
import com.bill.util.ILog
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.wheelview.adapter.BaseWheelAdapter

class NatureInfoWheelViewAdapter : BaseWheelAdapter<NatureInfo> {

    constructor(context: Context , list: List<NatureInfo>) : super(context, list, R.layout.natureinfo_wheelview_adapter)

    override fun getConvertView(convertView: View, list: List<NatureInfo>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.textView)

        val natureInfo = list[position]

        textView.text = natureInfo.natureName
    }

}