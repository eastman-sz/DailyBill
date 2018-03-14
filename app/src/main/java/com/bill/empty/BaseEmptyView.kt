package com.bill.empty

import android.content.Context
import android.view.View
import com.common.base.BaseRelativeLayout
import com.sz.kk.daily.bill.R
/**
 * Created by E on 2018/3/14.
 */
class BaseEmptyView : BaseRelativeLayout{

    constructor(context: Context) : super(context){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.base_empty_view , this)
    }

}