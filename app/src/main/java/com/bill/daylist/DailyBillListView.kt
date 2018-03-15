package com.bill.daylist

import android.content.Context
import android.view.View
import com.bill.base.BaseBillView
import com.sz.kk.daily.bill.R

/**
 * Created by E on 2018/3/15.
 */
class DailyBillListView : BaseBillView {

    constructor(context: Context) : super(context){
        init()
    }

    override fun initTitle() {

    }

    override fun initViews() {
        View.inflate(context , R.layout.daily_bill_list_view , this)
    }


}