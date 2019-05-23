package com.bill.daylist.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.nature.NatureInfo
import com.bill.dialog.DialogHelper
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.nature_filter_view.view.*
/**
 * 消费性质过滤。
 * @author E
 */
class NatureFilterView : BaseKotlinRelativeLayout{

    var natureId = 0

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.nature_filter_view , this)
    }

    override fun initListener() {
        natureFilterLayout.setOnClickListener {
            DialogHelper.showNatureInfoSelectDialog(context , object : OnCommonItemClickListener<NatureInfo>(){
                override fun onItemClick(it: NatureInfo) {
                    natureId = it.natureId
                    natureNameTextView.text = it.natureName

                }
            })
        }
    }

    fun reset(){
        this.natureId = 0
        natureNameTextView.text = "全部"
    }

}