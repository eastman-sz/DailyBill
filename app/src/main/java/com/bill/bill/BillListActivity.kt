package com.bill.bill

import android.os.Bundle
import com.common.base.BaseAppCompactActivitiy
import com.common.base.CommonTitleView
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_bill_list.*

class BillListActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_list)

        initActivitys()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("List")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
        })
    }

    override fun initViews() {
        val list = ArrayList<BillList>()
        val adapter = BillListAdapter(context , list)
        sticky_list.refreshableView.adapter = adapter

        val dailyBillList = DaiyBillDbHelper.getAllDailyBills()
        dailyBillList.forEach({
            val billList = BillList.fromBill(it)

            list.add(billList)
        })

        adapter.notifyDataSetChanged()
    }
}
