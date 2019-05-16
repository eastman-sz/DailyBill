package com.bill.add.consumption

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R

class ConsumptionView : BaseKotlinRelativeLayout {

    constructor(context: Context) : super(context){init()}

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){init()}

    override fun initViews() {
        View.inflate(context , R.layout.consumption_view , this)
    }

}