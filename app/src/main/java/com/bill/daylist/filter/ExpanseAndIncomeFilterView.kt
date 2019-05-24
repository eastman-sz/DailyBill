package com.bill.daylist.filter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.type.SuperType
import com.bill.util.ILog
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.expanse_and_income_filter_view.view.*
/**
 * Filter Expanse and Income.
 * @author E
 */
class ExpanseAndIncomeFilterView : BaseKotlinRelativeLayout {

    var onCommonItemClickListener : OnCommonItemClickListener<Int> ?= null

    var superType = SuperType.ALL.type

    constructor(context: Context) : super(context){init()}

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){init()}

    override fun initViews() {
        View.inflate(context , R.layout.expanse_and_income_filter_view , this)
    }

    override fun init() {
        super.init()
        Handler(Looper.getMainLooper()).postDelayed({
            allRadioButton.isChecked = true
        } , 50)
    }

    override fun initListener() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.allRadioButton ->{
                    superType = SuperType.ALL.type
                    onCommonItemClickListener?.onItemClick(SuperType.ALL.type)
                }
                R.id.expanseRadioButton ->{
                    superType = SuperType.Expense.type
                    onCommonItemClickListener?.onItemClick(SuperType.Expense.type)
                }
                R.id.incomeRadioButton ->{
                    superType = SuperType.Income.type
                    onCommonItemClickListener?.onItemClick(SuperType.Income.type)
                }
            }
        }
    }

    fun reset(){
        superType = SuperType.ALL.type
        allRadioButton.isChecked = true
    }


}