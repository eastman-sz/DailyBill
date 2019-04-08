package com.bill.bill

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.bill.base.BaseKotlinActivity
import com.bill.base.OnCommonRequestListener
import com.bill.consumption.AddConsumptionActivity
import com.bill.dialog.DialogHelper
import com.bill.empty.BaseEmptyView
import com.bill.util.ILog
import com.common.base.OnCommonTitleClickListener
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_bill_list.*

class BillListActivity : BaseKotlinActivity() {

    var bookId = 0L
    val list = ArrayList<BillList>()
    var adapter : BillListAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_list)
        initActivity()
    }

    override fun getIntentData() {
        bookId = intent.getLongExtra("bookId" , 0)
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("消费帐单")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
        }
    }

    override fun initViews() {
        adapter = BillListAdapter(context , list)
        sticky_list.refreshableView.adapter = adapter

        addEmptyView(sticky_list.refreshableView)

        sticky_list.refreshableView.setOnItemClickListener { _, _, position, _ ->
            val billList = list[position -1]

            ILog.e("billList: " + billList.amount)
        }

        sticky_list.refreshableView.setOnItemLongClickListener { parent, view, position, id ->
            DialogHelper.showCommonDialog(context , "确定要删除此条记录吗？" , "确定" , "取消" , object : OnCommonItemClickListener<Int>(){
                override fun onItemClick(it: Int) {
                    when(it){
                        0 ->{
                            val newPosition = position -1
                            val billList = list[newPosition]

                            DailyBillDbHelper.delete(billList.bid)

                            runOnUiThread{
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
