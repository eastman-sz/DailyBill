package com.bill.daylist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.bill.base.BaseBillView
import com.bill.bill.BillList
import com.bill.bill.BillListAdapter
import com.bill.bill.DaiyBillDbHelper
import com.bill.empty.BaseEmptyView
import com.bill.util.BroadcastAction
import com.common.dialog.CommonDialog
import com.common.dialog.OnCommonDialogBtnClickListener
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import com.utils.lib.ss.common.MathUtil
import kotlinx.android.synthetic.main.daily_bill_list_view.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by E on 2018/3/15.
 */
class DailyBillListView : BaseBillView {

    val list = ArrayList<BillList>()
    var adapter : BillListAdapter?= null

    constructor(context: Context) : super(context){
        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("明细")
        commonTitleView.setLeftBtnVisibility(View.INVISIBLE)
        commonTitleView.setRightBtnVisibility(View.INVISIBLE)
    }

    override fun initViews() {
        View.inflate(context , R.layout.daily_bill_list_view , this)

        adapter = BillListAdapter(context , list)
        sticky_list.refreshableView.adapter = adapter

        addEmptyView(sticky_list.refreshableView)

        sticky_list.refreshableView.setOnItemLongClickListener { parent, view, position, id ->
            val dialog = CommonDialog(context)
            dialog.show()
            dialog.setDialogText("提示" , "确定要删除此条记录吗？" , "确定" , "取消")
            dialog.setOnCommonDialogBtnClickListener(object : OnCommonDialogBtnClickListener {
                override fun onLeftBtnClik() {
                    val newPosition = position -1
                    val billList = list[newPosition]

                    DaiyBillDbHelper.delete(billList.bid)

                    (context as Activity).
                    runOnUiThread({
                        list.removeAt(newPosition)
                        adapter?.notifyDataSetChanged()
                    })
                }
                override fun onRightBtnClik() {
                }
            })

            true
        }
    }

    private fun freshBillListData(){
        doAsync {

            val dataList = ArrayList<BillList>()

            //每月总额
            val monthAmountMap = HashMap<String , Float>();

            val dailyBillList = DaiyBillDbHelper.getAllDailyBills()
            dailyBillList.forEach({
                val billList = BillList.fromBill(it)

                dataList.add(billList)

                val mmOfYear = DateHepler.timestampFormat(billList.billtime , "yyyy-MM") as String
                val amount = billList.amount

                if (monthAmountMap.containsKey(mmOfYear)){

                    val monthAmount = monthAmountMap[mmOfYear] as Float
                    monthAmountMap.put(mmOfYear , MathUtil.addF(monthAmount , amount , 2))

                }else{

                    monthAmountMap.put(mmOfYear , amount)
                }
            })

            //
            dataList.forEach {
                val mmOfYear = DateHepler.timestampFormat(it.billtime , "yyyy-MM") as String

                it.monthAmount = monthAmountMap[mmOfYear] as Float

            }

            dataList.sort()

            uiThread {
                list.clear()
                list.addAll(dataList)

                adapter?.notifyDataSetChanged()
            }
        }
    }


    private fun addEmptyView(listview: ListView){
        val emptyView = listview.emptyView
        if (null != emptyView){
            return
        }
        val newEmptyView = BaseEmptyView(context)
        (listview.parent as ViewGroup).addView(newEmptyView)
        listview.emptyView = newEmptyView
        newEmptyView.setEmptyText("Nothing")
    }

    override fun freshByHand(forceUpdate: Boolean) {
        freshBillListData()
    }

    override fun addBroadCastAction(): java.util.ArrayList<String> {
        val list = java.util.ArrayList<String>()
        list.add(BroadcastAction.NEW_ADD_CONSUMPTION)
        return list
    }

    override fun onBroadCastReceive(context: Context?, action: String?, intent: Intent?) {
        when(action){
            BroadcastAction.NEW_ADD_CONSUMPTION -> {
                freshBillListData()
            }
        }
    }


}