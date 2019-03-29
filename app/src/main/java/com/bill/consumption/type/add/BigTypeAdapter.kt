package com.bill.consumption.type.add

import android.content.Context
import android.view.View
import com.bill.consumption.type.BigType
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R

class BigTypeAdapter : IBaseAdapter<BigType>{

    constructor(context: Context , list: List<BigType>) : super(context, list, R.layout.bigtype_adapter)

    override fun getConvertView(convertView: View, list: List<BigType>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.textView)

        val bigType = list[position]

        textView.text = bigType.typeName
    }

}