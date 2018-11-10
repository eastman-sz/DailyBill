package com.bill.bill

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.bill.base.OnCommonRequestListener
import com.bill.consumption.AddConsumptionActivity
import com.bill.empty.BaseEmptyView
import com.bill.util.ILog
import com.common.base.BaseAppCompactActivitiy
import com.common.base.CommonTitleView
import com.common.dialog.CommonDialog
import com.common.dialog.OnCommonDialogBtnClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_bill_list.*

class BillListActivity : BaseAppCompactActivitiy() {

    var bookId = 0L
    val list = ArrayList<BillList>()
    var adapter : BillListAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_list)

        initActivitys()
    }

    override fun getIntentData() {
        bookId = intent.getLongExtra("bookId" , 0)
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("消费帐单")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
        })
    }

    override fun initViews() {
        adapter = BillListAdapter(context , list)
        sticky_list.refreshableView.adapter = adapter

        addEmptyView(sticky_list.refreshableView)

        sticky_list.refreshableView.setOnItemClickListener { parent, view, position, id ->
            val billList = list[position -1]

            ILog.e("billList: " + billList.amount)
        }

        sticky_list.refreshableView.setOnItemLongClickListener { parent, view, position, id ->
            val dialog = CommonDialog(context)
            dialog.show()
            dialog.setDialogText("提示" , "确定要删除此条记录吗？" , "确定" , "取消")
            dialog.setOnCommonDialogBtnClickListener(object : OnCommonDialogBtnClickListener{
                override fun onLeftBtnClik() {
                    val newPosition = position -1
                    val billList = list[newPosition]

                    DaiyBillDbHelper.delete(billList.bid)

                    runOnUiThread{
                        list.removeAt(newPosition)
                        adapter?.notifyDataSetChanged()
                    }
                }
                override fun onRightBtnClik() {
                }
            })

            true
        }
    }

    private fun freshBillListData(){
        DailyBillDataFetchHelper.getAllDailyBills(bookId , object : OnCommonRequestListener<List<BillList>>(){
            override fun onSuccess(it: List<BillList>) {
                list.clear()
                list.addAll(it)
                adapter?.notifyDataSetChanged()
            }

        })

    }

    fun onBtnClick(view : View){
        when(view){
            addBillBtnTextView -> {
                startActivity(Intent(context , AddConsumptionActivity::class.java).putExtra("bookId" , bookId))
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

    override fun onStart() {
        super.onStart()
        freshBillListData()
    }

}
