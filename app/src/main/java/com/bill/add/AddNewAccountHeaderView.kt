package com.bill.add

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.add_new_account_header_view.view.*

class AddNewAccountHeaderView : BaseKotlinRelativeLayout {

    var onCommonItemClickListener : OnCommonItemClickListener<Int> ?= null

    private var color0 = 0
    private var color1 = 0

    constructor(context: Context) : super(context){init()}

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){init()}

    override fun initViews() {
        View.inflate(context , R.layout.add_new_account_header_view , this)

        color0 = ContextCompat.getColor(context , R.color.c2)
        color1 = ContextCompat.getColor(context , R.color.c14)

    }

    override fun initListener() {
        item0TextView.setOnClickListener {
            onItemChange(0)
            onCommonItemClickListener?.onItemClick(0)
        }
        item1TextView.setOnClickListener {
            onItemChange(1)
            onCommonItemClickListener?.onItemClick(1)
        }
    }

    fun onItemChange(item : Int){
        item0TextView.setTextColor(if (0 == item) color0 else color1)
        line1ImgView.visibility = if (0 == item) View.VISIBLE else View.GONE

        item1TextView.setTextColor(if (1 == item) color0 else color1)
        line2ImgView.visibility = if (1 == item) View.VISIBLE else View.GONE
    }



}