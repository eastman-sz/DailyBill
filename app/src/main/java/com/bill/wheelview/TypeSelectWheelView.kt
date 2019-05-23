package com.bill.wheelview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.consumption.type.*
import com.bill.consumption.type.add.OnTypeFreshBroadcastReceiveListener
import com.bill.consumption.type.add.TypeFreshBroadcastReceiveListener
import com.sz.kk.daily.bill.R
import com.wheelview.HorizontalWheelView
import com.wheelview.OnWheelScrollListener
import kotlinx.android.synthetic.main.type_select_wheelview.view.*

class TypeSelectWheelView : BaseKotlinRelativeLayout {

    private val bigList = ArrayList<BigType>()
    private val smallList = ArrayList<SmallType>()
    private var superType = SuperType.Expense.type

    private val typeFreshBroadcastReceiveListener = TypeFreshBroadcastReceiveListener()

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context , attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.type_select_wheelview , this)
    }

    fun showType(superType : Int){
        this.superType = superType
        freshAll()
    }

    private fun freshAll(){
        bigList.clear()
        bigList.addAll(BigTypeDbHelper.getBigTypeS(superType))

        val bigTypeAdapter = BigTypeWheelViewAdapter(context , bigList)
        bigTypeWheelView.viewAdapter = bigTypeAdapter
        bigTypeWheelView.visibleItems = 7
        bigTypeWheelView.setCurrentItem(0 , false)
        bigTypeWheelView.addScrollingListener(object : OnWheelScrollListener {
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
        smallList.addAll(SmallTypeDbHelper.getSmallTypeS(superType , bigTypeId))

        val smallTypeAdapter = SmallTypeWheelViewAdapter(context , smallList)
        smallTypeWheelView.viewAdapter = smallTypeAdapter
        smallTypeWheelView.visibleItems = 7
        smallTypeWheelView.setCurrentItem(1 , false)
        smallTypeWheelView.addScrollingListener(object : OnWheelScrollListener{
            override fun onScrollingStarted(wheelView: HorizontalWheelView) {
            }
            override fun onScrollingFinished(wheelView: HorizontalWheelView) {

            }
        })
    }

    override fun initListener() {
        typeFreshBroadcastReceiveListener.onTypeFreshBroadcastReceiveListener = object : OnTypeFreshBroadcastReceiveListener(){
            override fun onBigTypeFresh() {
                freshAll()
            }
            override fun onSmallTypeFresh() {
                Handler(Looper.getMainLooper()).postDelayed({
                    freshAll()
                } , 600)
            }
        }
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

    override fun onDetachedFromWindow() {
        typeFreshBroadcastReceiveListener.unRegister()
        super.onDetachedFromWindow()
    }

}