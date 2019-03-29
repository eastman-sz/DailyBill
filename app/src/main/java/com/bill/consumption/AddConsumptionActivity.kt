package com.bill.consumption

import android.os.Bundle
import android.view.View
import com.bill.base.BaseKotlinActivity
import com.bill.bill.DaiyBillDbHelper
import com.bill.consumption.type.BigType
import com.bill.consumption.type.OnConsumptionTypeSelectListener
import com.bill.consumption.type.SmallType
import com.bill.dialog.ConsumptionPointDialog
import com.bill.dialog.DateTimeSelectDialog
import com.bill.dialog.DialogHelper
import com.bill.point.ConsumptionPoint
import com.bill.util.BroadcastAction
import com.bill.util.BroadcastUtil
import com.common.base.OnCommonTitleClickListener
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import kotlinx.android.synthetic.main.activity_add_consumption.*

class AddConsumptionActivity : BaseKotlinActivity() {

    var cTimestamp : Long = 0L;

    var amountRight = false
    var timeRight = false
    var marketId = 0

    var bookId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_consumption)
        initActivity()
    }

    init {
        cTimestamp = System.currentTimeMillis()
    }

    override fun getIntentData() {
        bookId = intent.getLongExtra("bookId" , 0)
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

    fun onBtnClick(v : View){
        when(v){
            dateTimeLayout -> {
                DialogHelper.showDateTimeSelectDialog(context , cTimestamp , object : DateTimeSelectDialog.OnDateTimeSelectedListener{
                    override fun onSelected(timestamp: Long) {
                        runOnUiThread {
                            cTimestamp = timestamp
                            timeRight = true
                            dateTimeTextView.text = DateHepler.timestampFormat(timestamp , "yyyy-MM-dd HH:mm:ss")

                            freshBtn()
                        }
                    }
                })
            }

            consumptionPointLayout ->{
                val dialog = ConsumptionPointDialog(context)
                dialog.show()
                dialog.onConsuptionPointSelectListener = object : ConsumptionPointDialog.OnConsuptionPointSelectListener{
                    override fun selected(consumptionPoint: ConsumptionPoint) {
                        marketId = consumptionPoint.marketId

                        runOnUiThread {
                            pointNameTextView.text = consumptionPoint.marketName
                        }

                        freshBtn()
                    }
                }
            }

            typeLayout ->{
                //分类选择
                DialogHelper.showTypeSelectDialog(context , object : OnConsumptionTypeSelectListener{
                    override fun onTypeSelect(smallType: SmallType, bigType: BigType) {
                        runOnUiThread {

                            typeNameTextView.text = bigType.typeName.plus("  >  ").plus(smallType.typeName)
                        }
                    }
                })
            }

            saveBtnTextView -> {
                val amount = amountTextView.text.toString().toFloat()
                val remarks = remarksTextView.text.toString()

                DaiyBillDbHelper.save(bookId, amount , cTimestamp , marketId , remarks)

                onBackPressed()

                BroadcastUtil.sendBroadCast(BroadcastAction.NEW_ADD_CONSUMPTION)
            }
        }
    }

    fun freshBtn(){
        val right = amountRight && timeRight && marketId > 0
        when(right){
            false -> {
                saveBtnTextView.setBackgroundResource(R.drawable.gray_disabled_retangle_selector)
                saveBtnTextView.isEnabled = false
            }

            true -> {
                saveBtnTextView.setBackgroundResource(R.drawable.c1_c2_retangle_selector)
                saveBtnTextView.isEnabled = true
            }

        }
    }


}
