package com.bill.summary.bar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.bill.DailyBill
import com.bill.bill.DailyBillDbHelper
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bar_chart_market_view.view.*
/**
 * BarChart show data grouped by nature.
 * @author E
 */
class BarChartNatureView : BaseKotlinRelativeLayout {

    private val list = ArrayList<DailyBill>()
    private var adapter : BarChartNatureAdapter ?= null

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.bar_chart_nature_view , this)

        adapter = BarChartNatureAdapter(context , list)
        listView.adapter = adapter

        getData()
    }

    private fun getData(){
        list.addAll(DailyBillDbHelper.getGroupByNature())
        adapter?.notifyDataSetChanged()
    }


}