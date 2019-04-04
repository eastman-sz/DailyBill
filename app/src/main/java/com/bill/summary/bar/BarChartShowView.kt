package com.bill.summary.bar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
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
    }



}