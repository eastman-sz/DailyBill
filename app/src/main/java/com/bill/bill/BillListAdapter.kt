package com.bill.bill

import android.content.Context
import android.view.View
import com.bill.util.CommonUtil
import com.common.base.CustomFontDigitTextView
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.handmark.base.IBaseStickyListAdapter
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
/**
 * Created by E on 2018/3/9.
 */
class BillListAdapter : IBaseStickyListAdapter<BillList> {

    constructor(context: Context , list : List<BillList>) :
            super(context , list , R.layout.bill_list_adapter_view , R.layout.adapter_header_view)

    override fun getConvertView(convertView: View?, list: MutableList<BillList>?, position: Int) {
        val amountTextView = ViewHolder.getView(convertView , R.id.amountTextView) as CustomFontDigitTextView
        val marketTextView = ViewHolder.getView(convertView , R.id.marketTextView) as CustomFontTextView
        val timeTextView = ViewHolder.getView(convertView , R.id.timeTextView) as CustomFontTextView

        val billList = list?.get(position)
        val amount = billList?.amount
        val billTime = billList?.billtime as Long

        amountTextView.text = CommonUtil.trimLastZero(amount.toString())
        timeTextView.text = DateHepler.timestampFormat(billTime , "MM-dd HH:mm:ss")
    }

    override fun getHeaderConvertView(convertView: View?, list: MutableList<BillList>?, position: Int) {
        val headerTextView = ViewHolder.getView(convertView , R.id.headerTextView) as CustomFontTextView

        val billList = list?.get(position)
        val billTime = billList?.billtime as Long

        headerTextView.text = DateHepler.timestampFormat(billTime , "yyyy年MM月")

    }

}