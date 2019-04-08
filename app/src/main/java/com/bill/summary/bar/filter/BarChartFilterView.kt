package com.bill.summary.bar.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bar_chart_filter_view.view.*
import org.jetbrains.anko.startActivity
/**
 * Filter view for bar chart.
 * @author E
 */
class BarChartFilterView : BaseKotlinRelativeLayout{

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.bar_chart_filter_view , this)
    }

    override fun initListener() {
        filterLayout.setOnClickListener {
            context.startActivity<BarChartFilterActivity>()
        }
    }



}