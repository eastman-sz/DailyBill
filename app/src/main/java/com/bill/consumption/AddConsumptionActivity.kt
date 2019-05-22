package com.bill.consumption

import android.os.Bundle
import android.view.View
import com.bill.base.BaseKotlinActivity
import com.bill.bill.DailyBillDbHelper
import com.bill.billbook.BillBook
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
import com.common.base.OnCommonTitleClickListener
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import kotlinx.android.synthetic.main.activity_add_consumption.*

class AddConsumptionActivity : BaseKotlinActivity() {

    var billTime : Long = 0L //创建时间

    var amountRight = false
    var timeRight = false

    var marketId = 0 //地点ID
    var bookId = 0 //帐本
    var bigTypeId = 0 //二级分类ID
    var smallTypeId = 0 //二级分类ID
    var natureId = 0 //性质ID
    var paymentId = 0 //支付方式

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_consumption)
        initActivity()
    }

    init {
        billTime = System.currentTimeMillis()/1000
    }

    override fun getIntentData() {
        bookId = intent.getIntExtra("bookId" , 0)
    }

    override fun initTitle() {
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.setCenterTitle("新增消费")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
        }
    }

    override fun initListener() {
        amountTextView.onAmountEditTextListener = object : OnAmountEditTextListener{
            override fun onEditListener(isRight: Boolean, text: String) {
                amountRight = isRight
                freshBtn()
            }
        }
    }

    override fun initViews() {
        //添加默认值
        //帐本：默认
        bookId = 0
        bookTextView.text = "默认"
        //默认时间:当前时间
        dateTimeTextView.text = DateHepler.timestampFormat(billTime , "yyyy年MM月dd日 HH:mm:ss")
        timeRight = true
        //默认分类 吃> 中餐
        bigTypeId = 1
        smallTypeId = 12
        typeNameTextView.text = "吃  >  中餐"
        //默认性质:日常消费
        natureId = 1
        natureTextView.text = "日常消费"
        //默认消费地点:沃尔玛
        marketId = 1
        pointNameTextView.text = "沃尔玛"
        //支付方式
        paymentId = 1
        paymentTextView.text = "现金"

        freshBtn()
    }

    fun onBtnClick(v : View){
        when(v){
            dateTimeLayout -> {
                DialogHelper.showDateTimeSelectDialog(context , billTime , object : DateTimeSelectDialog.OnDateTimeSelectedListener{
                    override fun onSelected(timestamp: Long) {
                        runOnUiThread {
                            billTime = timestamp
                            timeRight = true
                            dateTimeTextView.text = DateHepler.timestampFormat(timestamp , "yyyy年MM月dd日 HH:mm:ss")
                            freshBtn()
                        }
                    }
                })
            }

            consumptionPointLayout ->{
                //商场选择
                DialogHelper.showMarketSelectDialog(context , object : OnCommonItemClickListener<Market>(){
                    override fun onItemClick(it: Market) {
                        marketId = it.marketId

                        runOnUiThread {
                            pointNameTextView.text = it.marketName
                        }

                        freshBtn()
                    }
                })
            }

            typeLayout ->{
                //分类选择
                DialogHelper.showTypeSelectDialog(context , SuperType.Expense.type , object : OnConsumptionTypeSelectListener{
                    override fun onTypeSelect(smallType: SmallType, bigType: BigType) {
                        runOnUiThread {
                            bigTypeId = bigType.typeId
                            smallTypeId = smallType.typeId

                            typeNameTextView.text = bigType.typeName.plus("  >  ").plus(smallType.typeName)
                        }

                        freshBtn()
                    }
                })
            }

            natureLayout ->{
                //性质：日常消费...
                DialogHelper.showNatureInfoSelectDialog(context , object : OnCommonItemClickListener<NatureInfo>(){
                    override fun onItemClick(it: NatureInfo) {
                        runOnUiThread {
                            natureId = it.natureId

                            natureTextView.text = it.natureName
                        }

                        freshBtn()
                    }
                })
            }

            paymentLayout ->{
                //支付方式
                DialogHelper.showPaymentSelectDialog(context , object : OnCommonItemClickListener<Payment>(){
                    override fun onItemClick(it: Payment) {
                        paymentId = it.paymentId
                        runOnUiThread {
                            paymentTextView.text = it.paymentName
                        }
                    }
                })
            }

            bookLayout ->{
                //帐本：默认
                DialogHelper.showBillBookSelectDialog(context , object : OnCommonItemClickListener<BillBook>(){
                    override fun onItemClick(it: BillBook) {
                        bookId = it.bookId
                        runOnUiThread {
                            bookTextView.text = it.name
                        }
                        freshBtn()
                    }
                })

            }

            saveBtnTextView -> {
                val amount = amountTextView.text.toString().toFloat()
                val remarks = remarksTextView.text.toString()

                DailyBillDbHelper.saveExpense(bookId , amount, billTime , remarks , marketId , bigTypeId , smallTypeId , natureId , paymentId)

                onBackPressed()

                BroadcastUtil.sendBroadCast(BroadcastAction.NEW_ADD_CONSUMPTION)
            }
        }
    }

    fun freshBtn(){
        val right = amountRight && timeRight && marketId > 0 && bigTypeId > 0 && smallTypeId> 0 && natureId > 0
        saveBtnTextView.setBackgroundResource(if (right) R.drawable.c1_c2_retangle_selector else R.drawable.gray_disabled_retangle_selector)
        saveBtnTextView.isEnabled = right
    }


}
