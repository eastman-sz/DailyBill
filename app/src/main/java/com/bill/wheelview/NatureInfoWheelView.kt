package com.bill.wheelview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.consumption.nature.NatureInfo
import com.bill.consumption.nature.NatureInfoDbHelper
import com.common.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.natureinfo_wheelview.view.*

class NatureInfoWheelView : BaseKotlinRelativeLayout {

    private val list = ArrayList<NatureInfo>()

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context , attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.natureinfo_wheelview , this)
    }

    fun showNatureInfoS(){
        list.clear()
        list.addAll(NatureInfoDbHelper.getNatures())

        val adapter = NatureInfoWheelViewAdapter(context, list)
        wheelView.viewAdapter = adapter
        wheelView.visibleItems = 5
        wheelView.setCurrentItem(1 , false)
    }

    fun getNatureInfo() : NatureInfo{
        val curPosition = wheelView.currentItem
        val natureInfo = list[curPosition]
        return natureInfo
    }

}