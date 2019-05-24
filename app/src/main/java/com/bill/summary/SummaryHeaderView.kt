package com.bill.summary

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.type.SuperType
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.summary_header_view.view.*

class SummaryHeaderView : BaseKotlinRelativeLayout {

    var onCommonItemClickListener : OnCommonItemClickListener<Int> ?= null

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.summary_header_view , this)

        Handler(Looper.getMainLooper()).postDelayed({
            onItemClick(SuperType.Expense.type)
        } , 200)
    }

    override fun initListener() {
        expanseLayout.setOnClickListener {
            onItemClick(SuperType.Expense.type)
        }
        incomeLayout.setOnClickListener {
            onItemClick(SuperType.Income.type)
        }
    }

    private fun onItemClick(item : Int){
        expanseLayout.alpha = if (item == SuperType.Expense.type) 1f else 0.2f
        expanseLine.visibility = if (item == SuperType.Expense.type) View.VISIBLE else View.GONE

        incomeLayout.alpha = if (item == SuperType.Income.type) 1f else 0.2f
        incomeLine.visibility = if (item == SuperType.Income.type) View.VISIBLE else View.GONE

        onCommonItemClickListener?.onItemClick(item)
    }


}