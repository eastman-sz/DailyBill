package com.bill.dialog

import android.content.Context
import android.os.Bundle
import com.common.dialog.BaseUpGlideDialog
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHelper
import kotlinx.android.synthetic.main.date_time_select_dialog_view.*
/**
 * Created by E on 2018/3/8.
 */
class DateTimeSelectDialog : BaseUpGlideDialog {

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_time_select_dialog_view)

        init()
    }

    fun setTimestamp(timestamp: Long){
        dateTimeSelectWheelview.setTimestamp(timestamp)
    }

    override fun initListeners() {
        sureBtnTextView.setOnClickListener {
            dismiss()

            val currentDate = dateTimeSelectWheelview.currentDate
            val timeStamp = DateHelper.dateFormatString2Date(currentDate , "yyyy-MM-dd HH:mm:ss").time

            onDateTimeSelectedListener?.onSelected(timeStamp/1000)
        }
    }

    interface OnDateTimeSelectedListener{
        fun onSelected(timestamp: Long)
    }

    var onDateTimeSelectedListener :OnDateTimeSelectedListener ?= null

}