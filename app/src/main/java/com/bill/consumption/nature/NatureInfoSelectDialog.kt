package com.bill.consumption.nature

import android.content.Context
import android.os.Bundle
import com.common.dialog.BaseUpGlideDialog
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.natureinfo_select_dialog.*
/**
 * 消费性质。
 * @author E
 */
class NatureInfoSelectDialog : BaseUpGlideDialog {

    var onCommonItemClickListener : OnCommonItemClickListener<NatureInfo> ?= null

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.natureinfo_select_dialog)

        init()
    }

    override fun initViews() {
        natureInfoWheelView.showNatureInfoS()
    }

    override fun initListeners() {
        sureBtnTextView.setOnClickListener {
            dismiss()
            val natureInfo = natureInfoWheelView.getNatureInfo()

            onCommonItemClickListener?.onItemClick(natureInfo)
        }
    }
}