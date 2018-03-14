package com.bill.main

import android.os.Bundle
import com.bill.billbook.BillbookView
import com.bill.summary.SummaryView
import com.bill.util.ILog
import com.common.base.BaseAppCompactActivitiy
import com.common.base.BasePagerAdapter
import com.common.base.BaseRelativeLayout
import com.common.base.IonPageChangeListener
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

        viewPager.addOnPageChangeListener(object : IonPageChangeListener(){
            override fun onPageSelected(index: Int) {
                ILog.e("---index--: " + index)

                runOnUiThread {
                    item1TextView.setTextColor(if (0 == index){resources.getColor(R.color.c8) }else{resources.getColor(R.color.c13)})
                    item2TextView.setTextColor(if (1 == index){resources.getColor(R.color.c8) }else{resources.getColor(R.color.c13)})
                }
            }
        })
    }

}
