package com.bill.summary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.bill.DailyBillDbHelper
import com.bill.consumption.type.SuperType
import com.bill.util.ColorHelper
import com.bill.util.CommonUtil
import com.common.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHelper
import kotlinx.android.synthetic.main.total_summary_view.view.*
import java.math.BigDecimal
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

    fun getPeriodSummary(superType: Int){
        showTextColor(superType)

        val cTime = System.currentTimeMillis()
        //今日
        val todayStartTimestamp = DateHelper.getDayStartTimestamp(cTime)
        val todayEndTimestamp = DateHelper.getDayEndTimestamp(cTime)
        val todayTotalAmount = DailyBillDbHelper.getDailyBillAmount(superType , todayStartTimestamp , todayEndTimestamp)
        //本周
        val weekStartTimestamp = DateHelper.getDayStartTimestamp(DateHelper.getWeekStartTimestamp(cTime))
        val weekEndTimestamp = DateHelper.getDayEndTimestamp(DateHelper.getWeekEndTimestamp(cTime))
        val weekTotalAmount = DailyBillDbHelper.getDailyBillAmount(superType , weekStartTimestamp , weekEndTimestamp)
        //本月
        val year = DateHelper.getYear()
        val month = DateHelper.getMonthOfYear()
        val monthStartTimestamp = DateHelper.getDayStartTimestamp(DateHelper.getFirstDayOfMonthInTimestamp(year , month))
        val monthEndTimestamp = DateHelper.getDayEndTimestamp(DateHelper.getLastDayOfMonthInTimestamp(year , month))
        val monthTotalAmount = DailyBillDbHelper.getDailyBillAmount(superType , monthStartTimestamp , monthEndTimestamp)
        //今年
        val day1OfYearStartTimestamp = DateHelper.getDayStartTimestamp(DateHelper.getFirstDayOfYear(year).time)
        val dayEndOfYearStartTimestamp = DateHelper.getDayEndTimestamp(DateHelper.getLastDayOfYear(year).time)
        val yearTotalAmount = DailyBillDbHelper.getDailyBillAmount(superType , day1OfYearStartTimestamp , dayEndOfYearStartTimestamp)

        setTotalSummary(todayTotalAmount , weekTotalAmount , monthTotalAmount , yearTotalAmount)
    }

    private fun showTextColor(superType: Int){
        val color1 = ColorHelper.getExpenseTextColor()
        val color2 = ColorHelper.getIncomeTextColor()

        when(superType){
            SuperType.Expense.type ->{
                yearAmountTextView.setTextColor(color1)
                todayAmountTextView.setTextColor(color1)
                weekAmountTextView.setTextColor(color1)
                monthAmountTextView.setTextColor(color1)
            }
            SuperType.Income.type ->{
                yearAmountTextView.setTextColor(color2)
                todayAmountTextView.setTextColor(color2)
                weekAmountTextView.setTextColor(color2)
                monthAmountTextView.setTextColor(color2)
            }
        }


    }

    private fun setTotalSummary(todayAmount : BigDecimal , weekAmount : BigDecimal , monthAmount : BigDecimal , yearAmount : BigDecimal){
        todayAmountTextView.text = CommonUtil.trimLastZero(todayAmount.toString())
        weekAmountTextView.text = CommonUtil.trimLastZero(weekAmount.toString())
        monthAmountTextView.text = CommonUtil.trimLastZero(monthAmount.toString())
        yearAmountTextView.text = CommonUtil.trimLastZero(yearAmount.toString())
    }

}