package com.bill.summary.bar

import android.content.Context
import android.util.SparseArray
import android.view.View
import com.bill.bill.DailyBill
import com.bill.consumption.martket.MarketDbHelper
import com.bill.consumption.payment.PaymentDbHelper
import com.bill.util.CommonUtil
import com.common.base.CustomFontDigitTextView
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.MathUtil

class BarChartPaymentAdapter : IBaseAdapter<DailyBill> {

    private var paymentNameArray : SparseArray<String>?= null
    private var totalAmount = 0f

    constructor(context: Context , list: List<DailyBill>) : super(context, list, R.layout.bar_chart_market_adapter){
        paymentNameArray =PaymentDbHelper.getPaymentNameArray()
    }

    override fun getConvertView(convertView: View, list: List<DailyBill>, position: Int) {
        val typeNameTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.typeNameTextView)
        val percentTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.percentTextView)
        val amountTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.amountTextView)
        val progressChartView = ViewHolder.getView<ProgressChartView>(convertView , R.id.progressChartView)

        val dailyBill = list[position]
        val paymentId = dailyBill.paymentId
        val amount = dailyBill.amount
        val percent = MathUtil.divideF (amount*100 , totalAmount , 2)

        typeNameTextView.text = paymentNameArray?.get(paymentId , "--")
        percentTextView.text = percent.toString().plus("%")
        amountTextView.text = CommonUtil.trimLastZero(amount.toString())

        progressChartView.setProgress(totalAmount , amount)
    }

    override fun notifyDataSetChanged() {
        totalAmount = 0F
        list.forEach {
            totalAmount += it.amount
        }
        super.notifyDataSetChanged()
    }

}