package com.bill.daylist.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.common.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.type_filter_view.view.*
/**
 * 消费分类过滤。
 * @author E
 */
class TypeFilterView : BaseKotlinRelativeLayout{

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.type_filter_view , this)
    }

    override fun initListener() {
        typeFilterLayout.setOnClickListener {

        }
    }

}