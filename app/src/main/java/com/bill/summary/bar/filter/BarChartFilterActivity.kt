package com.bill.summary.bar.filter

import android.os.Bundle
import com.bill.daylist.ListenerConfig
import com.common.base.BaseAppCompactActivity
import com.common.base.OnCommonTitleClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_bar_chart_filter.*

class BarChartFilterActivity : BaseAppCompactActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart_filter)
        initActivity()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("过滤")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                finish()
            }
        }
    }

    override fun initListener() {
        sureBtnTextView.setOnClickListener {
            finish()

            var startTimestamp = commonDateFilterView.startTimestamp
            var endTimestamp = commonDateFilterView.endTimestamp

            var barChartTypeId = barChartTypeFilterView.barChartTypeId
            var barChartTypeName = barChartTypeFilterView.barChartTypeName

            val barChartFilter = BarChartFilter()
            barChartFilter.startTimestamp = startTimestamp
            barChartFilter.endTimestamp = endTimestamp
            barChartFilter.barChartGroupId = barChartTypeId
            barChartFilter.barChartGroupName = barChartTypeName

            ListenerConfig.onBarChartFilterParamSetListener?.onResult(barChartFilter)
        }
    }



}
