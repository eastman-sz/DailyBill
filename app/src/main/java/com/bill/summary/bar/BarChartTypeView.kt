package com.bill.summary.bar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.bill.DailyBill
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bar_chart_type_view.view.*
/**
 * BarChartTypeView. Total data grouped by bigType.
 * @author E
 */
class BarChartTypeView : BaseKotlinRelativeLayout {

    private val list = ArrayList<DailyBill>()
    private var adapter : BarChartTypeAdapter ?= null

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

    }

}