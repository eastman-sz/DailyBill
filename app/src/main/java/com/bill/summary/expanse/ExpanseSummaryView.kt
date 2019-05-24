package com.bill.summary.expanse

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseBillView
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.NewAddConsumptionBroadcastReceiveListener
import com.bill.consumption.type.SuperType
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.expanse_summary_view.view.*

class ExpanseSummaryView : BaseBillView{

    constructor(context: Context) : super(context){init()}

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){init()}

    override fun initViews() {
        View.inflate(context , R.layout.expanse_summary_view , this)

        freshData()
    }

    override fun onSummaryRefresh() {
        freshData()
    }

    private fun freshData(){
        totalSummaryView.getPeriodSummary(SuperType.Expense.type)
    }


}