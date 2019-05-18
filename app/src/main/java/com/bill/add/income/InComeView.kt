package com.bill.add.income

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import com.bill.add.BaseAddBillView
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.billbook.BillBook
import com.bill.consumption.OnAmountEditTextListener
import com.bill.dialog.DialogHelper
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.income_view.view.*

class InComeView : BaseAddBillView{

    var bookId = 0 //帐本
    var incomeBigType = 0 //一级分类
    var incomeSmallType = 0 //二级分类

    var isNumberInputRight = false //金额是否已输入

    constructor(context: Context) : super(context){init()}

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){init()}

    override fun initViews() {
        View.inflate(context , R.layout.income_view , this)

        amountTextView.onAmountEditTextListener = object : OnAmountEditTextListener{
            override fun onEditListener(isRight: Boolean, text: String) {
                isNumberInputRight = isRight

                freshBtn()
            }
        }

        //帐本：默认
        bookId = 0
        bookTextView.text = "默认"
    }

    override fun initListener() {
        typeLayout.setOnClickListener(listener)
        bookLayout.setOnClickListener(listener)
    }

    private val mHandler = Handler(Looper.getMainLooper())

    private val listener = OnClickListener {
        when(it){
            typeLayout ->{

            }

            bookLayout ->{
                //帐本：默认
                DialogHelper.showBillBookSelectDialog(context , object : OnCommonItemClickListener<BillBook>(){
                    override fun onItemClick(it: BillBook) {
                        mHandler.post {
                            bookId = it.bookId
                            bookTextView.text = it.name
                        }
                    }
                })
            }


        }


    }


    fun freshBtn(){
        val right = isNumberInputRight
        saveBtnTextView.setBackgroundResource(if (right) R.drawable.c1_c2_retangle_selector else R.drawable.gray_disabled_retangle_selector)
        saveBtnTextView.isEnabled = right
    }

}