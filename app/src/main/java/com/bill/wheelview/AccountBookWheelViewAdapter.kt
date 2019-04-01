package com.bill.wheelview

import android.content.Context
import android.view.View
import com.bill.billbook.BillBook
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.wheelview.adapter.BaseWheelAdapter

class AccountBookWheelViewAdapter : BaseWheelAdapter<BillBook> {

    constructor(context: Context , list: List<BillBook>) :super(context, list, R.layout.account_book_wheelview_adapter)

    override fun getConvertView(convertView: View, list: List<BillBook>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.textView)

        val billBook = list[position]

        textView.text = billBook.name

    }

}