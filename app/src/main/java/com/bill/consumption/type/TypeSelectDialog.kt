package com.bill.consumption.type

import android.content.Context
import android.os.Bundle
import com.bill.dialog.BaseUpGlideDialog
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.type_select_dialog.*

class TypeSelectDialog : BaseUpGlideDialog {

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.type_select_dialog)

        init()
    }

    override fun initViews() {
        typeSelectWheelView.showType(1)
    }

}