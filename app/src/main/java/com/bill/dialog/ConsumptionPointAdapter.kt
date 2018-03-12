package com.bill.dialog

import android.content.Context
import android.view.View
import com.bill.point.ConsumptionPoint
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.sz.kk.daily.bill.R
/**
 * Created by E on 2018/3/12.
 */
class ConsumptionPointAdapter : IBaseAdapter<ConsumptionPoint> {

    constructor(context: Context , list : List<ConsumptionPoint>) : super(context , list , R.layout.consuption_point_adapter_view)

    override fun getConvertView(conterView: View?, list : MutableList<ConsumptionPoint>?, position: Int) {
        val textView = ViewHolder.getView<CustomFontTextView>(conterView, R.id.textView)

        val consumptionPoint = list?.get(position)
        val pointName = consumptionPoint?.marketName

        textView.text = pointName
    }

}