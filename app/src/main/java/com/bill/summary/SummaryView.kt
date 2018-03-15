package com.bill.summary

import android.content.Context
import android.content.Intent
import android.view.View
import com.bill.base.BaseBillView
import com.bill.bill.DaiyBillDbHelper
import com.bill.util.BroadcastAction
import com.bill.util.CommonUtil
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import kotlinx.android.synthetic.main.summary_view.view.*
import java.util.ArrayList
/**
 * Created by E on 2018/3/12.
 */
class SummaryView : BaseBillView{

    constructor(context: Context) : super(context){
        init()
    }

    override fun initTitle() {
        commonTitleView.setLeftBtnVisibility(View.INVISIBLE)
    }

    override fun initViews() {
        View.inflate(context , R.layout.summary_view , this)
    }

    override fun freshByHand(forceUpdate: Boolean) {
        freshData()
    }

    fun freshData(){
        val ctime = System.currentTimeMillis()
        //今日
        val todayStartTimestamp = DateHepler.getDayStartTimestamp(ctime)
        val todayEndTimestamp = DateHepler.getDayEndTimestamp(ctime)
        val todayBills = DaiyBillDbHelper.getDailyBills(todayStartTimestamp , todayEndTimestamp)
        //本周
        val weekStartTimestamp = DateHepler.getDayStartTimestamp(DateHepler.getWeekStartTimestamp(ctime))
        val weekEndTimestamp = DateHepler.getDayEndTimestamp(DateHepler.getWeekEndTimestamp(ctime))
        val weekBills = DaiyBillDbHelper.getDailyBills(weekStartTimestamp , weekEndTimestamp)
        //本月
        val year = DateHepler.getYear()
        val month = DateHepler.getMonthOfYear()
        val monthStartTimestamp = DateHepler.getDayStartTimestamp(DateHepler.getFirstdayOfMonthInTimestamp(year , month))
        val monthEndTimestamp = DateHepler.getDayEndTimestamp(DateHepler.getLastdayOfMonthInTimestamp(year , month))
        val monthBills = DaiyBillDbHelper.getDailyBills(monthStartTimestamp , monthEndTimestamp)
        //今年
        val day1OfYearStartTimestamp = DateHepler.getDayStartTimestamp(DateHepler.getFirstDayOfYear(year).time)
        val dayEndOfYearStartTimestamp = DateHepler.getDayEndTimestamp(DateHepler.getLastDayOfYear(year).time)
        val yearBills = DaiyBillDbHelper.getDailyBills(day1OfYearStartTimestamp , dayEndOfYearStartTimestamp)

        var todayAmount = 0f

        todayBills.forEach {
            todayAmount += it.amount
        }

        todayAmountTextView.text = CommonUtil.trimLastZero(todayAmount.toString())

        var weekAmount = 0f

        weekBills.forEach {
            weekAmount += it.amount
        }

        weekAmountTextView.text = CommonUtil.trimLastZero(weekAmount.toString())

        var monthAmount = 0f

        monthBills.forEach {
            monthAmount += it.amount
        }

        monthAmountTextView.text = CommonUtil.trimLastZero(monthAmount.toString())

        var yearAmount = 0f

        yearBills.forEach {
            yearAmount += it.amount
        }

        yearAmountTextView.text = CommonUtil.trimLastZero(yearAmount.toString())

    }

    override fun addBroadCastAction(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add(BroadcastAction.NEW_ADD_CONSUMPTION)
        return list
    }

    override fun onBroadCastReceive(context: Context?, action: String?, intent: Intent?) {
        when(action){
            BroadcastAction.NEW_ADD_CONSUMPTION -> {
                freshData()
            }
        }
    }

}