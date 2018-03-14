package com.bill.summary

import android.content.Context
import android.view.View
import com.bill.base.BaseBillView
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.summary_view.view.*
/**
 * Created by E on 2018/3/12.
 */
class SummaryView : BaseBillView{

    constructor(context: Context) : super(context){
        init()
    }

    override fun initTitle() {
        commonTitleView.setLeftBtnVisibility(View.INVISIBLE)
    }

    override fun initViews() {
        View.inflate(context , R.layout.summary_view , this)
    }


}