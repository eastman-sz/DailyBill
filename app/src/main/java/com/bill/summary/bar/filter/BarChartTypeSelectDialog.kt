package com.bill.summary.bar.filter

import android.content.Context
import android.os.Bundle
import com.bill.wheelview.barChart.BarChart
import com.common.dialog.BaseUpGlideDialog
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bar_chart_type_select_dialog.*
/**
 * 条形图要展示的数据的类型选择.
 * @author E
 */
class BarChartTypeSelectDialog : BaseUpGlideDialog {

    var onCommonItemClickListener : OnCommonItemClickListener<BarChart>?= null

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bar_chart_type_select_dialog)

        init()
    }

    override fun initListeners() {
        sureBtnTextView.setOnClickListener {
            init()

            val barChart = barChartTypeWheelView.getBarChart()
            onCommonItemClickListener?.onItemClick(barChart)
        }
    }

}