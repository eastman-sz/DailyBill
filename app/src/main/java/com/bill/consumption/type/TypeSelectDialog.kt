package com.bill.consumption.type

import android.content.Context
import android.os.Bundle
import com.bill.dialog.BaseUpGlideDialog
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.type_select_dialog.*

class TypeSelectDialog : BaseUpGlideDialog {

    var onConsumptionTypeSelectListener : OnConsumptionTypeSelectListener ?= null

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.type_select_dialog)

        init()
    }

    override fun initViews() {
        typeSelectWheelView.showType(1)
    }

    override fun initListeners() {
        sureBtnTextView.setOnClickListener {
            dismiss()
            val smallType = typeSelectWheelView.getSmallType()
            val bigType = typeSelectWheelView.getBigType()
            onConsumptionTypeSelectListener?.onTypeSelect(smallType , bigType)
        }
    }

}