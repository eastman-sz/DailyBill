package com.bill.consumption.type

import android.content.Context
import android.os.Bundle
import com.bill.consumption.type.add.EditTypeActivity
import com.common.dialog.BaseUpGlideDialog
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.type_select_dialog.*
import org.jetbrains.anko.startActivity

class TypeSelectDialog : BaseUpGlideDialog {

    var onConsumptionTypeSelectListener : OnConsumptionTypeSelectListener ?= null

    private var superType = SuperType.Expense.type

    constructor(context: Context , superType : Int) : super(context){
        this.superType = superType
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.type_select_dialog)

        init()
    }

    override fun initViews() {
        typeSelectWheelView.showType(superType)
    }

    override fun initListeners() {
        editTypeTextView.setOnClickListener {
            context.startActivity<EditTypeActivity>()
        }

        sureBtnTextView.setOnClickListener {
            dismiss()
            val smallType = typeSelectWheelView.getSmallType()
            val bigType = typeSelectWheelView.getBigType()
            onConsumptionTypeSelectListener?.onTypeSelect(smallType , bigType)
        }
    }

}