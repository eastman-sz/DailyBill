package com.bill.summary.detail

import android.os.Bundle
import com.bill.base.BaseNewKotlinActivity
import com.bill.base.OnCommonRequestListener
import com.bill.bill.BillList
import com.bill.bill.BillListAdapter
import com.bill.bill.DailyBillDataFetchHelper
import com.bill.consumption.type.BigTypeDbHelper
import com.bill.consumption.type.SuperType
import com.bill.util.ILog
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_fiter_detail.*

class FilterDetailActivity : BaseNewKotlinActivity() {

    val list = ArrayList<BillList>()
    var adapter : BillListAdapter?= null

    private var startTimestamp = 0L
    private var endTimestamp = 0L
    private var superType = SuperType.Expense.type
    private var bigType = 0
    private var smallType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiter_detail)
        initActivity()

        getData()
    }

    override fun getIntentData() {
        superType = intent.getIntExtra("superType" , SuperType.Expense.type)
        bigType = intent.getIntExtra("bigType" , 0)
        smallType = intent.getIntExtra("smallType" , 0)
        startTimestamp = intent.getLongExtra("startTimestamp" , 0L)
        endTimestamp = intent.getLongExtra("endTimestamp" , 0L)
    }

    override fun initTitle() {
        val bigTypeNameArray = BigTypeDbHelper.getBigTypeNameArray(superType)
        centerTitleTextView.text = bigTypeNameArray[bigType , "--"]
    }

    override fun initViews() {
        adapter = BillListAdapter(context!! , list)
        pullToRefreshStickyListView.refreshableView.adapter = adapter

        adapter?.type = if (superType == SuperType.Expense.type) 1 else 2
    }

    override fun initListener() {
        returnImgView.setOnClickListener {
            finish()
        }
    }

    private fun getData(){
        DailyBillDataFetchHelper.getSmallTypeBillList(superType , smallType , startTimestamp , endTimestamp , object : OnCommonRequestListener<List<BillList>>(){
            override fun onSuccess(it: List<BillList>) {
                ILog.e("========几条数据==================:: ${it.size}")
                list.clear()
                list.addAll(it)
                adapter?.notifyDataSetChanged()
            }
        })
    }

}
