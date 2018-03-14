package com.bill.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bill.bill.BillListActivity
import com.bill.billbook.BillbookView
import com.bill.consumption.AddConsumptionActivity
import com.bill.summary.SummaryView
import com.common.base.BaseAppCompactActivitiy
import com.common.base.BasePagerAdapter
import com.common.base.BaseRelativeLayout
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivitys()
    }

    override fun initViews() {
        val list = ArrayList<BaseRelativeLayout>()
        list.add(BillbookView(context))
        list.add(SummaryView(context))

        val adapter = BasePagerAdapter(context , list)
        viewPager.adapter = adapter
    }

    fun onBtnClick(v : View){
        when(v){
            addBtnTextView ->{
                startActivity(Intent(context , AddConsumptionActivity::class.java))
            }

            listBtnTextView -> {
                startActivity(Intent(context , BillListActivity::class.java))
            }
        }
    }

}
