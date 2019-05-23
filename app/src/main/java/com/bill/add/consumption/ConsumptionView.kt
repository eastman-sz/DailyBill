package com.bill.add.consumption

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import com.bill.add.BaseAddBillView
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.bill.DailyBillDbHelper
import com.bill.billbook.BillBook
import com.bill.consumption.OnAmountEditTextListener
import com.bill.consumption.martket.Market
import com.bill.consumption.nature.NatureInfo
import com.bill.consumption.payment.Payment
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
import kotlinx.android.synthetic.main.activity_add_consumption.*
import kotlinx.android.synthetic.main.consumption_view.view.*

class ConsumptionView : BaseAddBillView {

    var billTime : Long = 0L //创建时间

    var amountRight = false
    var timeRight = false

    var marketId = 0 //地点ID
    var bookId = 0 //帐本
    var bigTypeId = 0 //二级分类ID
    var smallTypeId = 0 //二级分类ID
    var natureId = 0 //性质ID
    var paymentId = 0 //支付方式

    constructor(context: Context) : super(context){init()}

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){init()}

    override fun initViews() {
        View.inflate(context , R.layout.consumption_view , this)

        amountTextView.onAmountEditTextListener = object : OnAmountEditTextListener {
            override fun onEditListener(isRight: Boolean, text: String) {
                amountRight = isRight
                freshBtn()
            }
        }

        //添加默认值
        //帐本：默认
        bookId = 0
        bookTextView.text = "默认"
        //默认时间:当前时间
        dateTimeTextView.text = DateHepler.timestampFormat(billTime , "yyyy年MM月dd日 HH:mm")
        timeRight = true
        //默认分类 食品酒水 > 中餐
        bigTypeId = 1
        smallTypeId = 101
        typeNameTextView.text = "食品酒水  >  中餐"
        //默认性质:日常消费
        natureId = 1
        natureTextView.text = "日常消费"
        //默认消费地点:沃尔玛
        marketId = 1
        pointNameTextView.text = "沃尔玛"
        //支付方式
        paymentId = 1
        paymentTextView.text = "现金"
    }

    init {
        billTime = System.currentTimeMillis()/1000
    }

    override fun initListener() {
        dateTimeLayout.setOnClickListener(listener)
        typeLayout.setOnClickListener(listener)
        consumptionPointLayout.setOnClickListener(listener)
        natureLayout.setOnClickListener(listener)
        paymentLayout.setOnClickListener(listener)
        bookLayout.setOnClickListener(listener)
        saveBtnTextView.setOnClickListener(listener)

    }

    private val mHandler = Handler(Looper.getMainLooper())

    private val listener = OnClickListener {
        when(it){
            dateTimeLayout ->{
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
                //分类选择
                DialogHelper.showTypeSelectDialog(context , SuperType.Expense.type , object : OnConsumptionTypeSelectListener {
                    override fun onTypeSelect(smallType: SmallType, bigType: BigType) {
                        mHandler.post {
                            bigTypeId = bigType.typeId
                            smallTypeId = smallType.typeId

                            typeNameTextView.text = bigType.typeName.plus("  >  ").plus(smallType.typeName)

                            freshBtn()
                        }
                    }
                })
            }
            consumptionPointLayout ->{
                //商场选择
                DialogHelper.showMarketSelectDialog(context , object : OnCommonItemClickListener<Market>(){
                    override fun onItemClick(it: Market) {
                        mHandler.post {
                            marketId = it.marketId
                            pointNameTextView.text = it.marketName

                            freshBtn()
                        }
                    }
                })
            }
            natureLayout ->{
                //性质：日常消费...
                DialogHelper.showNatureInfoSelectDialog(context , object : OnCommonItemClickListener<NatureInfo>(){
                    override fun onItemClick(it: NatureInfo) {
                        mHandler.post {
                            natureId = it.natureId

                            natureTextView.text = it.natureName

                            freshBtn()
                        }
                    }
                })
            }
            paymentLayout ->{
                //支付方式
                DialogHelper.showPaymentSelectDialog(context , object : OnCommonItemClickListener<Payment>(){
                    override fun onItemClick(it: Payment) {
                        mHandler.post {
                            paymentId = it.paymentId
                            paymentTextView.text = it.paymentName

                            freshBtn()
                        }
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

                            freshBtn()
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

    fun save(){
        val right = amountRight && timeRight && marketId > 0 && bigTypeId > 0 && smallTypeId> 0 && natureId > 0
        if (!right){
            return
        }

        val amount = amountTextView.text.toString().toFloat()
        val remarks = remarksTextView.text.toString()

        DailyBillDbHelper.saveExpense(bookId , amount, billTime , remarks , marketId , bigTypeId , smallTypeId , natureId , paymentId)
        //send broadcast
        BroadcastUtil.sendBroadCast(BroadcastAction.NEW_ADD_CONSUMPTION)

        ToastHelper.makeText(context , "保存成功")

        mHandler.postDelayed({
            (context as Activity).finish()
        } , 300)

    }

    fun freshBtn(){
        val right = amountRight && timeRight && marketId > 0 && bigTypeId > 0 && smallTypeId> 0 && natureId > 0
        saveBtnTextView.setBackgroundResource(if (right) R.drawable.c1_c2_retangle_selector else R.drawable.gray_disabled_retangle_selector)
        saveBtnTextView.isEnabled = right
    }

}