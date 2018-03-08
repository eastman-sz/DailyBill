package com.bill.dialog

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import com.common.dialog.BaseDialog
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import com.utils.lib.ss.info.DeviceInfo
import kotlinx.android.synthetic.main.date_time_select_dialog_view.*
/**
 * Created by E on 2018/3/8.
 */
class DateTimeSelectDialog : BaseDialog{

    constructor(context: Context) : super(context , R.style.lable_del_dialog)

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
            val timeStamp = DateHepler.dateFormatString2Date(currentDate , "yyyy-MM-dd HH:mm:ss").time

            onDateTimeSelectedListener?.onSelected(timeStamp)
        }
    }

    override fun show() {
        super.show()

        val attr = window.attributes
        attr.width = DeviceInfo.getScreenWith(context)
        window.attributes = attr

        window.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.share_style)
    }

    interface OnDateTimeSelectedListener{
        fun onSelected(timestamp: Long)
    }

    var onDateTimeSelectedListener :OnDateTimeSelectedListener ?= null

}