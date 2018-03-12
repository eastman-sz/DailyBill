package com.bill.consumption

import android.os.Bundle
import android.util.Log
import android.view.View
import com.bill.bill.DaiyBillDbHelper
import com.bill.dialog.ConsuptionPointDialog
import com.bill.dialog.DateTimeSelectDialog
import com.bill.point.ConsumptionPoint
import com.common.base.BaseAppCompactActivitiy
import com.common.base.CommonTitleView
import com.common.base.ITextChangedListener
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import kotlinx.android.synthetic.main.activity_add_consumption.*

class AddConsumptionActivity : BaseAppCompactActivitiy() {

    var cTimestamp : Long = 0L;

    var amountRight = false
    var timeRight = false
    var marketId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_consumption)

        initActivitys()
    }

    init {
        cTimestamp = System.currentTimeMillis()
    }

    override fun initTitle() {
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.setCenterTitleText("")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
        })
    }

    override fun initListener() {
        amountTextView.addTextChangedListener(object : ITextChangedListener(){
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val right = checkAmountRight()
                amountRight = right

                freshBtn()

                Log.e("ilog" , "是否正确:  " + right)

                if (right){
                    return
                }
                val msg = s.toString()
                var length = msg.length
                if (length == 0){
                    return
                }
                val startWithPoint = msg.startsWith("." ,true)
                if (startWithPoint){
                    val text = msg.substring(1)
                    amountTextView.setText(text)
                    amountTextView.setSelection(0)
                    return
                }

                val selectionEnd = amountTextView.selectionEnd
                if (selectionEnd == length){
                    val text = msg.substring(0 , length -1)
                    amountTextView.setText(text)
                    amountTextView.setSelection(length -1)

                }else{
                    val text = msg.substring(0 , selectionEnd -1)
                    val text2 = msg.substring(selectionEnd)

                    amountTextView.setText(text.plus(text2))
                    amountTextView.setSelection(selectionEnd -1)
                }
            }
        })
    }

    fun onBtnClick(v : View){
        when(v){
            dateTimeLayout -> {
                val dialog = DateTimeSelectDialog(context)
                dialog.show()
                dialog.setTimestamp(cTimestamp)
                dialog.onDateTimeSelectedListener = object : DateTimeSelectDialog.OnDateTimeSelectedListener{
                    override fun onSelected(timestamp: Long) {
                        runOnUiThread {
                            cTimestamp = timestamp
                            timeRight = true
                            dateTimeTextView.text = DateHepler.timestampFormat(timestamp , "yyyy-MM-dd HH:mm:ss")

                            freshBtn()
                        }
                    }
                }
            }

            consumptionPointLayout ->{
                val dialog = ConsuptionPointDialog(context)
                dialog.show()
                dialog.onConsuptionPointSelectListener = object : ConsuptionPointDialog.OnConsuptionPointSelectListener{
                    override fun selected(consumptionPoint: ConsumptionPoint) {
                        marketId = consumptionPoint.marketId

                        runOnUiThread {
                            pointNameTextView.text = consumptionPoint.marketName
                        }

                        freshBtn()
                    }
                }
            }

            saveBtnTextView -> {
                val amount = amountTextView.text.toString().toFloat()
                val remarks = remarksTextView.text.toString()

                DaiyBillDbHelper.save(amount , cTimestamp , marketId , remarks)

                onBackPressed()
            }
        }
    }

    fun checkAmountRight() : Boolean{
        val text = amountTextView.text.toString()
        if (text.isNullOrEmpty()){
            return false
        }
        if (text.startsWith(".",true)){
            return false
        }
        if (!text.contains(".")){
            return true
        }
        val right  = text.indexOf(".") == text.lastIndexOf(".")
        return right
    }

    fun freshBtn(){
        val right = amountRight && timeRight && marketId > 0
        when(right){
            false -> {
                saveBtnTextView.setBackgroundResource(R.drawable.gray_disabled_retangle_selector)
            }

            true -> {
                saveBtnTextView.setBackgroundResource(R.drawable.c1_c2_retangle_selector)
            }

        }
    }


}
