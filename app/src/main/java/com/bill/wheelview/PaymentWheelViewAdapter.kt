package com.bill.wheelview

import android.content.Context
import android.view.View
import com.bill.consumption.payment.Payment
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
import com.wheelview.adapter.BaseWheelAdapter

class PaymentWheelViewAdapter : BaseWheelAdapter<Payment> {

    constructor(context: Context , list: List<Payment>) : super(context, list, R.layout.natureinfo_wheelview_adapter)

    override fun getConvertView(convertView: View, list: List<Payment>, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.textView)

        val payment = list[position]

        textView.text = payment.paymentName
    }

}