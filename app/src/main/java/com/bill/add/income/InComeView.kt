package com.bill.add.income

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import com.bill.add.BaseAddBillView
import com.bill.bill.DailyBillDbHelper
import com.bill.billbook.BillBook
import com.bill.consumption.OnAmountEditTextListener
import com.bill.consumption.type.BigType
import com.bill.consumption.type.OnConsumptionTypeSelectListener
import com.bill.consumption.type.SmallType
import com.bill.consumption.type.SuperType
import com.bill.dialog.DateTimeSelectDialog
import com.bill.dialog.DialogHelper
import com.bill.util.BroadcastAction
import com.bill.util.BroadcastUtil
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import com.utils.lib.ss.common.ToastHelper
import kotlinx.android.synthetic.main.income_view.view.*

class InComeView : BaseAddBillView{

    var billTime : Long = 0L //创建时间
    var bookId = 0 //帐本
    var incomeBigType = 0 //一级分类
    var incomeSmallType = 0 //二级分类

    var timeRight = false
    var isNumberInputRight = false //金额是否已输入

    constructor(context: Context) : super(context){init()}

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){init()}

    init {
        billTime = System.currentTimeMillis()/1000

        incomeBigType = 1
        incomeSmallType = 10
    }

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

        dateTimeTextView.text = DateHepler.timestampFormat(billTime , "yyyy年MM月dd日 HH:mm")
        timeRight = true

        typeNameTextView.text = "职业收入 > 工资收入"
    }

    override fun initListener() {
        dateTimeLayout.setOnClickListener(listener)
        typeLayout.setOnClickListener(listener)
        bookLayout.setOnClickListener(listener)
        saveBtnTextView.setOnClickListener(listener)
    }

    private val mHandler = Handler(Looper.getMainLooper())

    private val listener = OnClickListener {
        when(it){
            dateTimeLayout ->{
                //时间
                DialogHelper.showDateTimeSelectDialog(context , billTime , object : DateTimeSelectDialog.OnDateTimeSelectedListener{
                    override fun onSelected(timestamp: Long) {
                        mHandler.post {
                            billTime = timestamp
                            timeRight = true
                            dateTimeTextView.text = DateHepler.timestampFormat(timestamp , "yyyy年MM月dd日 HH:mm")

                            freshBtn()
                        }
                    }
                })
            }

            typeLayout ->{
                //分类
                DialogHelper.showTypeSelectDialog(context , SuperType.Income.type , object : OnConsumptionTypeSelectListener{
                    override fun onTypeSelect(smallType: SmallType, bigType: BigType) {
                        incomeBigType = bigType.typeId
                        incomeSmallType = smallType.typeId

                        typeNameTextView.text = bigType.typeName.plus("  >  ").plus(smallType.typeName)
                    }
                })
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

            saveBtnTextView ->{
                //保存
                save()
            }
        }
    }


    fun freshBtn(){
        val right = isNumberInputRight
        saveBtnTextView.setBackgroundResource(if (right) R.drawable.c1_c2_retangle_selector else R.drawable.gray_disabled_retangle_selector)
        saveBtnTextView.isEnabled = right
    }

    fun save(){
        val amount = amountTextView.text.toString().toFloat()
        DailyBillDbHelper.saveIncome(bookId , amount , billTime , "" , incomeBigType , incomeSmallType)

        //send broadcast
        BroadcastUtil.sendBroadCast(BroadcastAction.NEW_ADD_CONSUMPTION)

        ToastHelper.makeText(context , "保存成功")

        mHandler.postDelayed({
            (context as Activity).finish()
        } , 300)
    }

}