package com.bill.wheelview.barChart

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.account_book_wheelview.view.*

class BarChartTypeWheelView : BaseKotlinRelativeLayout {

    private val list = ArrayList<BarChart>()

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.bar_chart_type_wheelview , this)

        showBarCharts()
    }

    private fun showBarCharts(){
        createBarCharts()

        val adapter = BarChartTypeWheelViewAdapter(context, list)
        wheelView.viewAdapter = adapter
        wheelView.visibleItems = 5
        wheelView.setCurrentItem(1 , false)
    }

    fun getBarChart() : BarChart {
        val curPosition = wheelView.currentItem
        val barChart = list[curPosition]
        return barChart
    }

    private fun createBarCharts(){
        list.clear()

        val barChart1 = BarChart(1 , "分类")
        val barChart2 = BarChart(2 , "商家")
        val barChart3 = BarChart(3 , "消费性质")
        val barChart4 = BarChart(4 , "支付方式")
        val barChart5 = BarChart(5 , "帐本")

        list.add(barChart1)
        list.add(barChart2)
        list.add(barChart3)
        list.add(barChart4)
        list.add(barChart5)
    }

}