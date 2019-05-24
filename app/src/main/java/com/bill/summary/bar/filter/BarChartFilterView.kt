package com.bill.summary.bar.filter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseBillView
import com.bill.bill.DailyBillDbHelper
import com.bill.consumption.type.SuperType
import com.bill.dialog.DialogHelper
import com.bill.util.BroadcastHelper
import com.bill.util.ColorHelper
import com.bill.util.CommonUtil
import com.bill.util.ViewHelper
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHelper
import kotlinx.android.synthetic.main.bar_chart_filter_view.view.*
/**
 * Filter view for bar chart.
 * @author E
 */
class BarChartFilterView : BaseBillView{

    var onBarChartFilterParamSetListener : OnBarChartFilterParamSetListener ?= null

    private var superType = SuperType.Expense.type

    private var startTimestamp = 0L
    private var endTimestamp = 0L

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

        startTimestamp = day1OfYearStartTimestamp
        endTimestamp = dayEndOfYearStartTimestamp

        initGroupName("分类")
    }

    override fun initListener() {
        filterLayout.setOnClickListener {
            if (superType != SuperType.Expense.type){

                return@setOnClickListener
            }
            DialogHelper.showBarChartFilter(context , object : OnBarChartFilterParamSetListener{
                override fun onResult(it: BarChartFilter) {
                    startTimestamp = it.startTimestamp
                    endTimestamp = it.endTimestamp

                    initTimeStamp(it.startTimestamp , it.endTimestamp)

                    initGroupName(it.barChartGroupName)

                    showTotalAmount()

                    //callback it to out
                    BroadcastHelper.onFilterTimeRangeChanged(it.startTimestamp , it.endTimestamp)

                    onBarChartFilterParamSetListener?.onResult(it)
                }
            })
        }
    }

    override fun onSummaryRefresh() {
        Handler(Looper.getMainLooper()).post {
            showTotalAmount()
        }
    }

    fun setViewType(superType: Int){
        this.superType = superType
        when(superType){
            SuperType.Expense.type ->{
                totalAmountTextView.setTextColor(ColorHelper.getExpenseTextColor())
            }
            SuperType.Income.type ->{
                totalAmountTextView.setTextColor(ColorHelper.getIncomeTextColor())
                ViewHelper.setLeftCompoundDrawables(context , totalAmountTextView , 0)
            }
        }

        showTotalAmount()
    }

    private fun showTotalAmount(){
        val totalAmount = DailyBillDbHelper.getDailyBillAmount(superType , startTimestamp , endTimestamp)
        totalAmountTextView.text = CommonUtil.trimLastZero(totalAmount.toString())
    }

    private fun initTimeStamp(startTimestamp : Long , endTimestamp : Long){
        durationTextView.text = "时间范围: ${DateHelper.timestampFormat(startTimestamp , "yyyy年MM月dd日")} ~ " +
                "${DateHelper.timestampFormat(endTimestamp , "yyyy年MM月dd日")}"
    }

    private fun initGroupName(groupName : String?){
        typeNameTextView.text = "数据分组: 按 '$groupName' 划分"
    }

}