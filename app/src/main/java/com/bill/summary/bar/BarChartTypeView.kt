package com.bill.summary.bar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.bill.DailyBill
import com.bill.bill.DailyBillDbHelper
import com.bill.consumption.type.SuperType
import com.bill.summary.BaseSummaryView
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bar_chart_type_view.view.*
/**
 * BarChartTypeView. Total data grouped by bigType.
 * @author E
 */
class BarChartTypeView : BaseSummaryView {

    private val list = ArrayList<DailyBill>()
    private var adapter : BarChartTypeAdapter ?= null

    var superType = SuperType.Expense.type

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.bar_chart_type_view , this)

        adapter = BarChartTypeAdapter(context , list)
        listView.adapter = adapter

        getData()
    }

    private fun getData(){
        list.clear()
        list.addAll(DailyBillDbHelper.getPeriodGroupByBigType(superType , startTimestamp , endTimestamp))
        adapter?.notifyDataSetChanged()
    }

    override fun onSummaryRefresh() {
        getData()
    }

}