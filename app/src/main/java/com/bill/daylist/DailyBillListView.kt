package com.bill.daylist

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.bill.base.BaseBillView
import com.bill.base.OnCommonRequestListener
import com.bill.bill.BillList
import com.bill.bill.BillListAdapter
import com.bill.bill.DailyBillDataFetchHelper
import com.bill.bill.DailyBillDbHelper
import com.bill.dialog.DialogHelper
import com.bill.empty.BaseEmptyView
import com.common.base.OnCommonTitleClickListener
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.daily_bill_list_view.view.*
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
        commonTitleView.setCenterTitle("明细")
        commonTitleView.setLeftBtnVisibility(View.INVISIBLE)
        commonTitleView.setRightBtnVisibility(View.VISIBLE)
        commonTitleView.setRightBtnText("筛选")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onRightBtnClick() {
                DialogHelper.showDailyBillFilter(context , object : OnDailyBillFilterParamSetListener{
                    override fun onResult(it : DailyBillFilter) {
                        onFilter(it)
                    }
                })
            }
        }
    }

    override fun initViews() {
        View.inflate(context , R.layout.daily_bill_list_view , this)

        adapter = BillListAdapter(context , list)
        sticky_list.refreshableView.adapter = adapter

        addEmptyView()

        sticky_list.refreshableView.setOnItemLongClickListener { _, _, position, _ ->
            DialogHelper.showCommonDialog(context , "确定要删除此条记录吗？" , "确定" , "取消" , object : OnCommonItemClickListener<Int>(){
                override fun onItemClick(it: Int) {
                    when(it){
                        0 ->{
                            val newPosition = position -1
                            val billList = list[newPosition]

                            DailyBillDbHelper.delete(billList.bid)
                            (context as Activity).
                                    runOnUiThread {
                                        list.removeAt(newPosition)
                                        adapter?.notifyDataSetChanged()
                                    }
                        }
                    }
                }
            })

            true
        }
    }

    override fun onSummaryRefresh() {
        freshBillListData()
    }

    private fun freshBillListData(){
        DailyBillDataFetchHelper.getAllDailyBills(object : OnCommonRequestListener<List<BillList>>(){
            override fun onSuccess(it: List<BillList>) {
                list.clear()
                list.addAll(it)
                adapter?.notifyDataSetChanged()
            }

        })
    }

    private fun onFilter(it : DailyBillFilter){
        DailyBillDataFetchHelper.getAllDailyBills(it, object : OnCommonRequestListener<List<BillList>>(){
            override fun onSuccess(it: List<BillList>) {
                list.clear()
                list.addAll(it)
                adapter?.notifyDataSetChanged()
            }

        })

    }

    private fun addEmptyView(){
        val listView = sticky_list.refreshableView
        val emptyView = listView.emptyView
        if (null != emptyView){
            return
        }
        val newEmptyView = BaseEmptyView(context)
        (listView.parent as ViewGroup).addView(newEmptyView)
        listView.emptyView = newEmptyView
        newEmptyView.setEmptyText("Nothing")
    }

    override fun freshByHand(forceUpdate: Boolean) {
        freshBillListData()
    }

}