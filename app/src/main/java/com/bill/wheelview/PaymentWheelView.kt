package com.bill.wheelview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.consumption.payment.Payment
import com.bill.consumption.payment.PaymentDbHelper
import com.common.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.natureinfo_wheelview.view.*
/**
 * 支付方式。
 * @author E
 */
class PaymentWheelView : BaseKotlinRelativeLayout {

    private val list = ArrayList<Payment>()

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context , attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.natureinfo_wheelview , this)
    }

    fun showPaymentS(){
        list.clear()
        list.addAll(PaymentDbHelper.getPayments())

        val adapter = PaymentWheelViewAdapter(context, list)
        wheelView.viewAdapter = adapter
        wheelView.visibleItems = 5
        wheelView.setCurrentItem(1 , false)
    }

    fun getPayment() : Payment{
        val curPosition = wheelView.currentItem
        val payment = list[curPosition]
        return payment
    }

}