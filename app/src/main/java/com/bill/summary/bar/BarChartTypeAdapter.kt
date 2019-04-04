package com.bill.summary.bar

import android.content.Context
import android.util.SparseArray
import android.view.View
import com.bill.bill.DailyBill
import com.bill.consumption.type.BigTypeDbHelper
import com.common.base.CustomFontDigitTextView
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
/**
 * Bar chart adapter.
 * @author E
 */
class BarChartTypeAdapter : IBaseAdapter<DailyBill> {

    private var bigTypeNameArray : SparseArray<String> ?= null

    constructor(context: Context , list: List<DailyBill>) : super(context, list, R.layout.bar_chart_type_adapter){
        bigTypeNameArray = BigTypeDbHelper.getBigTypeNameArray()
    }

    override fun getConvertView(convertView: View, list: List<DailyBill>, position: Int) {
        val typeNameTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.typeNameTextView)
        val percentTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.percentTextView)
        val amountTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.amountTextView)

        val dailyBill = list[position]
        val bigTypeId = dailyBill.bigTypeId

        typeNameTextView.text = bigTypeNameArray?.get(bigTypeId , "--")
    }

}