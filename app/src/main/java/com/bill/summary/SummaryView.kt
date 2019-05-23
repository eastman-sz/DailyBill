package com.bill.summary

import android.content.Context
import android.view.View
import com.bill.base.BaseBillView
import com.bill.consumption.type.SuperType
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.summary_view.view.*
/**
 * Created by E on 2018/3/12.
 */
class SummaryView : BaseBillView{

    constructor(context: Context) : super(context){
        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("简报")
        commonTitleView.setLeftBtnVisibility(View.INVISIBLE)
    }

    override fun initViews() {
        View.inflate(context , R.layout.summary_view , this)
    }

    override fun freshByHand(forceUpdate: Boolean) {
        freshData()
    }

    override fun onSummaryRefresh() {
        freshData()
    }

    private fun freshData(){
        totalSummaryView.getPeriodSummary(SuperType.Expense.type)
//        latest7DayChartView.freshData()
    }
}