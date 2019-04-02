package com.bill.consumption.payment

import android.content.Context
import android.os.Bundle
import com.common.dialog.BaseUpGlideDialog
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.payment_select_dialog.*
/**
 * 消费性质。
 * @author E
 */
class PaymentSelectDialog : BaseUpGlideDialog {

    var onCommonItemClickListener : OnCommonItemClickListener<Payment> ?= null

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_select_dialog)

        init()
    }

    override fun initViews() {
        paymentWheelView.showPaymentS()
    }

    override fun initListeners() {
        sureBtnTextView.setOnClickListener {
            dismiss()
            val payment = paymentWheelView.getPayment()

            onCommonItemClickListener?.onItemClick(payment)
        }
    }
}