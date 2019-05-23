package com.bill.daylist

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.dialog.DateTimeSelectDialog
import com.bill.dialog.DialogHelper
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHelper
import kotlinx.android.synthetic.main.common_date_filter_view.view.*
/**
 * @author E
 */
class CommonDateFilterView : BaseKotlinRelativeLayout {

    var startTimestamp = 0L
    var endTimestamp = 0L

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context , attrs : AttributeSet) : super(context , attrs){
        init()
    }


    override fun initViews() {
        View.inflate(context , R.layout.common_date_filter_view , this)
        //今年
        initTimestamp()
    }

    private fun initTimestamp(){
        val year = DateHelper.getYear()
        this.startTimestamp = DateHelper.getDayStartTimestamp(DateHelper.getFirstDayOfYear(year).time)
        this.endTimestamp = DateHelper.getDayEndTimestamp(DateHelper.getLastDayOfYear(year).time)

        showTimestamp()
    }

    override fun initListener() {
        todayTextView.setOnClickListener(listener)
        weekTextView.setOnClickListener(listener)
        monthTextView.setOnClickListener(listener)
        startTimeTextView.setOnClickListener(listener)
        endTimeTextView.setOnClickListener(listener)
    }

    private val listener = View.OnClickListener{
        when(it){
            todayTextView ->{
                val ctime = System.currentTimeMillis()/1000
                startTimestamp = DateHelper.getDayStartTimestamp(ctime)
                endTimestamp = DateHelper.getDayEndTimestamp(ctime)

                showTimestamp()
            }
            weekTextView ->{
                val ctime = System.currentTimeMillis()/1000
                startTimestamp = DateHelper.getWeekStartTimestamp(ctime)
                endTimestamp = DateHelper.getWeekEndTimestamp(ctime)

                startTimestamp = DateHelper.getDayStartTimestamp(startTimestamp)
                endTimestamp = DateHelper.getDayEndTimestamp(endTimestamp)

                showTimestamp()
            }
            monthTextView ->{
                val year = DateHelper.getYear()
                val month = DateHelper.getMonthOfYear()

                startTimestamp = DateHelper.getFirstDayOfMonthInTimestamp(year , month)
                endTimestamp = DateHelper.getLastDayOfMonthInTimestamp(year , month)

                startTimestamp = DateHelper.getDayStartTimestamp(startTimestamp)
                endTimestamp = DateHelper.getDayEndTimestamp(endTimestamp)

                showTimestamp()
            }
            startTimeTextView ->{
                DialogHelper.showDateTimeSelectDialog(context , startTimestamp , object : DateTimeSelectDialog.OnDateTimeSelectedListener{
                    override fun onSelected(timestamp: Long) {
                        startTimestamp = timestamp

                        showTimestamp()

                    }
                })
            }
            endTimeTextView ->{
                DialogHelper.showDateTimeSelectDialog(context , endTimestamp , object : DateTimeSelectDialog.OnDateTimeSelectedListener{
                    override fun onSelected(timestamp: Long) {
                        endTimestamp = timestamp

                        showTimestamp()

                    }
                })
            }
        }
    }

    private fun showTimestamp(){
        android.os.Handler(Looper.getMainLooper()).post {
            startTimeTextView.text = if (0L == startTimestamp ) "开始时间" else DateHelper.timestampFormat(startTimestamp , "yyyy-MM-dd HH:mm:ss")
        }
        Handler(Looper.getMainLooper()).post {
            endTimeTextView.text = if (0L == endTimestamp) "结束时间" else DateHelper.timestampFormat(endTimestamp , "yyyy-MM-dd HH:mm:ss")
        }
    }

    fun reset(){
        initTimestamp()
    }

}