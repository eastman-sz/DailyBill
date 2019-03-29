package com.bill.consumption.type.add

import android.content.Context
import android.view.View
import com.bill.consumption.type.BigType
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import org.jetbrains.anko.startActivity

class BigTypeAdapter : IBaseAdapter<BigType>{

    constructor(context: Context , list: List<BigType>) : super(context, list, R.layout.bigtype_adapter)

    override fun getConvertView(convertView: View, list: List<BigType>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.textView)
        val removeTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.removeTextView)
        val reNameTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.reNameTextView)

        val bigType = list[position]

        textView.text = bigType.typeName


        textView.setOnClickListener {
            context.startActivity<EditSmallTypeActivity>("bigTypeId" to bigType.typeId)
        }

        removeTextView.setOnClickListener {

        }

        reNameTextView.setOnClickListener {

        }
    }

}