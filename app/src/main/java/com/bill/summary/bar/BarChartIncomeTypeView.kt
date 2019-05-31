package com.bill.summary.bar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.bill.DailyBill
import com.bill.bill.DailyBillDbHelper
import com.bill.consumption.type.SuperType
import com.bill.summary.BaseSummaryView
import com.bill.summary.detail.FilterDetailActivity
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bar_chart_type_view.view.*
import org.jetbrains.anko.startActivity

/**
 * BarChartTypeView. Total data grouped by bigType.
 * @author E
 */
class BarChartIncomeTypeView : BaseSummaryView {

    private val list = ArrayList<DailyBill>()
    private var adapter : BarChartIncomeTypeAdapter ?= null

    var superType = SuperType.Income.type

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.bar_chart_type_view , this)

        adapter = BarChartIncomeTypeAdapter(context , list)
        listView.adapter = adapter

        getData()
    }

    private fun getData(){
        list.clear()
        list.addAll(DailyBillDbHelper.getPeriodGroupByBigType(superType , startTimestamp , endTimestamp))
        adapter?.notifyDataSetChanged()

        listView.setOnItemClickListener { _, _, position, _ ->
            val dailyBill = list[position]

            context.startActivity<FilterDetailActivity>("superType" to superType ,
                    "bigType" to dailyBill.bigTypeId ,
                    "smallType" to dailyBill.smallTypeId ,
                    "startTimestamp" to startTimestamp ,
                    "endTimestamp" to endTimestamp)

        }
    }

    override fun onSummaryRefresh() {
        getData()
    }

}