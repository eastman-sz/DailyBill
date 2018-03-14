package com.bill.empty

import android.content.Context
import android.view.View
import com.common.base.BaseRelativeLayout
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.base_empty_view.view.*
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

    fun setEmptyText(emptyText : String){
        emptyTextView.text = emptyText
    }

}