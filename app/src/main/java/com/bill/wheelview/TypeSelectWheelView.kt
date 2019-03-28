package com.bill.wheelview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.type.BigType
import com.bill.consumption.type.BigTypeDbHelper
import com.bill.consumption.type.SmallType
import com.bill.consumption.type.SmallTypeDbHelper
import com.sfs.wheelview.HorizontalWheelView
import com.sfs.wheelview.OnWheelScrollListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.type_select_wheelview.view.*

class TypeSelectWheelView : BaseKotlinRelativeLayout {

    val bigList = ArrayList<BigType>()
    val smallList = ArrayList<SmallType>()

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context , attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.type_select_wheelview , this)
    }

    fun showType(smallType : Int){
        bigList.addAll(BigTypeDbHelper.getBigTypeS())

        val bigTypeAdapter = BigTypeWheelViewAdapter(context , bigList)
        bigTypeWheelView.viewAdapter = bigTypeAdapter
        bigTypeWheelView.visibleItems = 7
        bigTypeWheelView.setCurrentItem(5 , false)
        bigTypeWheelView.addScrollingListener(object : OnWheelScrollListener{
            override fun onScrollingStarted(wheelView: HorizontalWheelView) {
            }
            override fun onScrollingFinished(wheelView: HorizontalWheelView) {
                onBigTypeScrollFinished()
            }
        })

        onBigTypeScrollFinished()
    }

    private fun onBigTypeScrollFinished(){
        initSmallTypeList()
    }

    private fun initSmallTypeList(){
        smallList.clear()
        val curPosition = bigTypeWheelView.currentItem
        val bigTypeId = bigList[curPosition].typeId
        smallList.addAll(SmallTypeDbHelper.getSmallTypeS(bigTypeId))

        val smallTypeAdapter = SmallTypeWheelViewAdapter(context , smallList)
        smallTypeWheelView.viewAdapter = smallTypeAdapter
        smallTypeWheelView.visibleItems = 7
        smallTypeWheelView.setCurrentItem(5 , false)
        smallTypeWheelView.addScrollingListener(object : OnWheelScrollListener{
            override fun onScrollingStarted(wheelView: HorizontalWheelView) {
            }
            override fun onScrollingFinished(wheelView: HorizontalWheelView) {

            }
        })
    }

    fun getBigType() : BigType{
        val curPosition = bigTypeWheelView.currentItem
        val bigType = bigList[curPosition]
        return bigType
    }

    fun getSmallType() : SmallType{
        val curPosition = smallTypeWheelView.currentItem
        val smallType = smallList[curPosition]
        return smallType
    }

}