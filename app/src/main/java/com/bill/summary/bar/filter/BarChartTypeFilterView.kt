package com.bill.summary.bar.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.dialog.DialogHelper
import com.bill.wheelview.barChart.BarChart
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bar_chart_type_filter_view.view.*

class BarChartTypeFilterView : BaseKotlinRelativeLayout{

    var barChartTypeId = 0

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.bar_chart_type_filter_view , this)
    }

    override fun initListener() {
        barChartTypeFilterLayout.setOnClickListener {
            DialogHelper.showBarChartTypeSelectDialog(context , object : OnCommonItemClickListener<BarChart>(){
                override fun onItemClick(it: BarChart) {
                    barChartTypeId = it.typeId
                    barChartNameTextView.text = it.name
                }
            })
        }
    }

}