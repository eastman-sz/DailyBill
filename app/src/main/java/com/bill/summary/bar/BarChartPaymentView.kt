package com.bill.summary.bar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.bill.DailyBill
import com.bill.bill.DailyBillDbHelper
import com.bill.summary.BaseSummaryView
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bar_chart_payment_view.view.*

class BarChartPaymentView : BaseSummaryView {

    private val list = ArrayList<DailyBill>()
    private var adapter : BarChartPaymentAdapter ?= null

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.bar_chart_payment_view , this)

        adapter = BarChartPaymentAdapter(context , list)
        listView.adapter = adapter

        getData()
    }

    private fun getData(){
        list.clear()
        list.addAll(DailyBillDbHelper.getPeriodGroupByPayment(startTimestamp , endTimestamp))
        adapter?.notifyDataSetChanged()
    }

    override fun onSummaryRefresh() {
        getData()
    }

}