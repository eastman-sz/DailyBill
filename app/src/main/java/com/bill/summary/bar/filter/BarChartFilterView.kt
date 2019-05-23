package com.bill.summary.bar.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.bill.DailyBillDbHelper
import com.bill.dialog.DialogHelper
import com.bill.util.BroadcastHelper
import com.bill.util.CommonUtil
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHelper
import kotlinx.android.synthetic.main.bar_chart_filter_view.view.*
/**
 * Filter view for bar chart.
 * @author E
 */
class BarChartFilterView : BaseKotlinRelativeLayout{

    var onBarChartFilterParamSetListener : OnBarChartFilterParamSetListener ?= null

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.bar_chart_filter_view , this)

        //今年
        val year = DateHelper.getYear()
        val day1OfYearStartTimestamp = DateHelper.getDayStartTimestamp(DateHelper.getFirstDayOfYear(year).time)
        val dayEndOfYearStartTimestamp = DateHelper.getDayEndTimestamp(DateHelper.getLastDayOfYear(year).time)

        initTimeStamp(day1OfYearStartTimestamp , dayEndOfYearStartTimestamp)

        initGroupName("分类")
    }

    override fun initListener() {
        filterLayout.setOnClickListener {
            DialogHelper.showBarChartFilter(context , object : OnBarChartFilterParamSetListener{
                override fun onResult(it: BarChartFilter) {
                    initTimeStamp(it.startTimestamp , it.endTimestamp)

                    initGroupName(it.barChartGroupName)

                    val totalAmount = DailyBillDbHelper.getDailyBillAmount(it.startTimestamp , it.endTimestamp)
                    totalAmountTextView.text = CommonUtil.trimLastZero(totalAmount.toString())

                    //callback it to out
                    BroadcastHelper.onFilterTimeRangeChanged(it.startTimestamp , it.endTimestamp)

                    onBarChartFilterParamSetListener?.onResult(it)
                }
            })
        }
    }

    private fun initTimeStamp(startTimestamp : Long , endTimestamp : Long){
        durationTextView.text = "时间范围: ${DateHelper.timestampFormat(startTimestamp , "yyyy年MM月dd日")} ~ " +
                "${DateHelper.timestampFormat(endTimestamp , "yyyy年MM月dd日")}"
    }

    private fun initGroupName(groupName : String?){
        typeNameTextView.text = "数据分组: 按 '$groupName' 划分"
    }

}