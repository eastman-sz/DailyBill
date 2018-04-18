package com.bill.bill

import android.content.Context
import android.util.SparseArray
import android.view.View
import com.bill.point.ConsumptionPoint
import com.bill.point.ConsumptionPointHelper
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

    var marketArray : SparseArray<ConsumptionPoint> ?= null

    constructor(context: Context , list : List<BillList>) :
            super(context , list , R.layout.bill_list_adapter_view , R.layout.adapter_header_view){
        marketArray = ConsumptionPointHelper.getAllConsuptionPointsArray()
    }

    override fun getConvertView(convertView: View?, list: MutableList<BillList>?, position: Int) {
        val amountTextView = ViewHolder.getView(convertView , R.id.amountTextView) as CustomFontDigitTextView
        val marketTextView = ViewHolder.getView(convertView , R.id.marketTextView) as CustomFontTextView
        val timeTextView = ViewHolder.getView(convertView , R.id.timeTextView) as CustomFontDigitTextView
        val remarksTextView = ViewHolder.getView(convertView , R.id.remarksTextView) as CustomFontTextView

        val billList = list?.get(position)
        val marketId = billList!!.marketId
        val amount = billList?.amount
        val billTime = billList?.billtime as Long
        val remarks = billList?.remarks
        val dayOfWeek = DateHepler.getDayOfWeekString(billTime)


        marketTextView.text = marketArray?.get(marketId)?.marketName
        amountTextView.text = CommonUtil.trimLastZero(amount.toString())
        timeTextView.text = DateHepler.timestampFormat(billTime , "MM-dd").plus("   ").plus(dayOfWeek)
        remarksTextView.text = "备注: ".plus(if (remarks.isEmpty()){"无"}else{remarks})
        remarksTextView.visibility = if (remarks.isEmpty()){View.GONE}else{View.VISIBLE}
    }

    override fun getHeaderConvertView(convertView: View?, list: MutableList<BillList>?, position: Int) {
        val headerTextView = ViewHolder.getView(convertView , R.id.headerTextView) as CustomFontTextView
        val monthAmountTextView = ViewHolder.getView(convertView , R.id.monthAmountTextView) as CustomFontDigitTextView

        val billList = list?.get(position)
        val billTime = billList?.billtime as Long
        val monthAmount = billList?.monthAmount

        headerTextView.text = DateHepler.timestampFormat(billTime , "yyyy年MM月")
        monthAmountTextView.text = "支出: ".plus(CommonUtil.trimLastZero(monthAmount.toString())).plus(" 元")
    }

}