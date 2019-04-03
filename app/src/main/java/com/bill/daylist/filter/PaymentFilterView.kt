package com.bill.daylist.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.payment.Payment
import com.bill.dialog.DialogHelper
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.payment_filter_view.view.*

/**
 * 支付方式过滤。
 * @author E
 */
class PaymentFilterView : BaseKotlinRelativeLayout {

    var paymentId = 0

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.payment_filter_view , this)
    }

    override fun initListener() {
        paymentFilterLayout.setOnClickListener {
            DialogHelper.showPaymentSelectDialog(context , object : OnCommonItemClickListener<Payment>(){
                override fun onItemClick(it: Payment) {
                    paymentId = it.paymentId
                    paymentNameTextView.text = it.paymentName

                }
            })
        }
    }

}