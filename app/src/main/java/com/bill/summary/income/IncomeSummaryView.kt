package com.bill.summary.income

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseBillView
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.type.SuperType
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.income_summary_view.view.*

class IncomeSummaryView : BaseBillView {

    constructor(context: Context) : super(context){init()}

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){init()}

    override fun initViews() {
        View.inflate(context , R.layout.income_summary_view , this)

        freshData()
    }

    override fun onSummaryRefresh() {
        freshData()
    }

    private fun freshData(){
        totalSummaryView.getPeriodSummary(SuperType.Income.type)
    }




}