package com.bill.consumption.type.add

import android.content.Context
import android.os.Bundle
import com.bill.consumption.type.BigType
import com.bill.consumption.type.BigTypeDbHelper
import com.bill.consumption.type.SuperType
import com.common.base.OnCommonTitleClickListener
import com.common.dialog.BaseFullScreenDialog
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bigtype_select_dialog.*

class BigTypeSelectDialog : BaseFullScreenDialog {

    var onCommonItemClickListener : OnCommonItemClickListener<BigType> ?= null

    var superType = SuperType.Expense.type

    constructor(context: Context , superType : Int) : super(context){
        this.superType = superType
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bigtype_select_dialog)

        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("一级分类选择")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                dismiss()
            }
        }
    }

    override fun initViews() {
        val list = ArrayList<BigType>()
        list.addAll(BigTypeDbHelper.getBigTypeS(superType))

        val adapter = BigTypeSelectAdapter(context, list)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, view, i, l ->
            dismiss()
            val bigType = list[i]
            onCommonItemClickListener?.onItemClick(bigType)
        }
    }



}