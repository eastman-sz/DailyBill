package com.bill.summary.bar

import android.content.Context
import android.util.SparseArray
import android.view.View
import com.bill.bill.DailyBill
import com.bill.billbook.BillBookDbHelper
import com.bill.util.CommonUtil
import com.common.base.CustomFontDigitTextView
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.MathUtil
import java.math.BigDecimal

class BarChartBookAdapter : IBaseAdapter<DailyBill>{

    private var billBookNameArray : SparseArray<String>?= null
    private var totalAmount = 0f

    constructor(context: Context , list: List<DailyBill>) : super(context, list, R.layout.bar_chart_book_adapter){
        billBookNameArray = BillBookDbHelper.getBillBookNameArray()
    }

    override fun getConvertView(convertView: View, list: List<DailyBill>, position: Int) {
        val typeNameTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.typeNameTextView)
        val percentTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.percentTextView)
        val amountTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.amountTextView)
        val progressChartView = ViewHolder.getView<ProgressChartView>(convertView , R.id.progressChartView)

        val dailyBill = list[position]
        val bookId = dailyBill.bookId
        val amount = dailyBill.amount
        val percent = MathUtil.divideF ((amount* BigDecimal(100)).toFloat() , totalAmount , 2)

        typeNameTextView.text = billBookNameArray?.get(bookId , "--")
        percentTextView.text = percent.toString().plus("%")
        amountTextView.text = CommonUtil.trimLastZero(amount.toString())

        progressChartView.setProgress(totalAmount , amount.toFloat())
    }

    override fun notifyDataSetChanged() {
        totalAmount = 0F
        list.forEach {
            totalAmount += it.amount.toFloat()
        }
        super.notifyDataSetChanged()
    }

}