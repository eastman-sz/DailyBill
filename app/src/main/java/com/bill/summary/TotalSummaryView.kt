package com.bill.summary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.bill.DailyBillDbHelper
import com.bill.util.CommonUtil
import com.common.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import kotlinx.android.synthetic.main.total_summary_view.view.*
/**
 * 总数概括性统计显示。
 * @author E
 */
class TotalSummaryView : BaseKotlinRelativeLayout {

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.total_summary_view , this)
    }

    fun getPeriodSummary(){
        val cTime = System.currentTimeMillis()
        //今日
        val todayStartTimestamp = DateHepler.getDayStartTimestamp(cTime)
        val todayEndTimestamp = DateHepler.getDayEndTimestamp(cTime)
        val todayTotalAmount = DailyBillDbHelper.getDailyBillAmount(todayStartTimestamp , todayEndTimestamp)
        //本周
        val weekStartTimestamp = DateHepler.getDayStartTimestamp(DateHepler.getWeekStartTimestamp(cTime))
        val weekEndTimestamp = DateHepler.getDayEndTimestamp(DateHepler.getWeekEndTimestamp(cTime))
        val weekTotalAmount = DailyBillDbHelper.getDailyBillAmount(weekStartTimestamp , weekEndTimestamp)
        //本月
        val year = DateHepler.getYear()
        val month = DateHepler.getMonthOfYear()
        val monthStartTimestamp = DateHepler.getDayStartTimestamp(DateHepler.getFirstdayOfMonthInTimestamp(year , month))
        val monthEndTimestamp = DateHepler.getDayEndTimestamp(DateHepler.getLastdayOfMonthInTimestamp(year , month))
        val monthTotalAmount = DailyBillDbHelper.getDailyBillAmount(monthStartTimestamp , monthEndTimestamp)
        //今年
        val day1OfYearStartTimestamp = DateHepler.getDayStartTimestamp(DateHepler.getFirstDayOfYear(year).time)
        val dayEndOfYearStartTimestamp = DateHepler.getDayEndTimestamp(DateHepler.getLastDayOfYear(year).time)
        val yearTotalAmount = DailyBillDbHelper.getDailyBillAmount(day1OfYearStartTimestamp , dayEndOfYearStartTimestamp)

        setTotalSummary(todayTotalAmount , weekTotalAmount , monthTotalAmount , yearTotalAmount)
    }

    private fun setTotalSummary(todayAmount : Float , weekAmount : Float , monthAmount : Float , yearAmount : Float){
        todayAmountTextView.text = CommonUtil.trimLastZero(todayAmount.toString())
        weekAmountTextView.text = CommonUtil.trimLastZero(weekAmount.toString())
        monthAmountTextView.text = CommonUtil.trimLastZero(monthAmount.toString())
        yearAmountTextView.text = CommonUtil.trimLastZero(yearAmount.toString())
    }

}