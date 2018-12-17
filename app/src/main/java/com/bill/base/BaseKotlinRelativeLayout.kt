package com.bill.base

import android.content.Context
import android.util.AttributeSet
import com.common.base.BaseRelativeLayout

open class BaseKotlinRelativeLayout : BaseRelativeLayout {


    constructor(context: Context) : super(context)

    constructor(context: Context , attrs : AttributeSet) : super(context , attrs)
}