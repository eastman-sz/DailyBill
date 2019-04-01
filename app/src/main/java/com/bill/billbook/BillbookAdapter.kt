package com.bill.billbook

import android.content.Context
import android.view.View
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
/**
 * Created by E on 2018/3/13.
 */
class BillbookAdapter : IBaseAdapter<BillBook>{

    constructor(context: Context , list: List<BillBook>) : super(context , list , R.layout.bill_book_adapter_view)

    override fun getConvertView(conterView: View?, list: MutableList<BillBook>?, postion: Int) {
        val bookNameTextView = ViewHolder.getView(conterView , R.id.bookNameTextView) as CustomFontTextView

        val billbook = list?.get(postion)
        val name = billbook?.name

        bookNameTextView.text = name
    }
}