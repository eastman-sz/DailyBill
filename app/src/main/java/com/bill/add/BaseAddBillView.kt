package com.bill.add

import android.content.Context
import android.util.AttributeSet
import com.bill.base.BaseKotlinRelativeLayout

open class BaseAddBillView : BaseKotlinRelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs)

    open fun saveBill(){}

}