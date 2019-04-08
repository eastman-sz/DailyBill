package com.bill.summary.bar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.summary.BaseSummaryView
import com.bill.summary.bar.filter.BarChartFilter
import com.bill.summary.bar.filter.OnBarChartFilterParamSetListener
import com.common.base.BasePagerAdapter
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bar_chart_show_view.view.*
/**
 * This is a container including all barChart View.
 * @author E
 */
class BarChartShowView : BaseKotlinRelativeLayout {

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.bar_chart_show_view , this)

        val list = ArrayList<BaseSummaryView>()
        list.add(BarChartTypeView(context))
        list.add(BarChartMarketView(context))
        list.add(BarChartNatureView(context))
        list.add(BarChartPaymentView(context))
        list.add(BarChartBookView(context))

        val adapter = BasePagerAdapter<BaseSummaryView>(context , list)
        viewPager.adapter = adapter


        barChartFilterView.onBarChartFilterParamSetListener = object : OnBarChartFilterParamSetListener{
            override fun onResult(it: BarChartFilter) {
                viewPager.setCurrentItem(it.barChartGroupId , false)
            }
        }
    }



}