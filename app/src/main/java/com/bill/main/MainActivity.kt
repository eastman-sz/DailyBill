package com.bill.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bill.billbook.BillbookView
import com.bill.consumption.AddConsumptionActivity
import com.bill.daylist.DailyBillListView
import com.bill.summary.SummaryView
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
        list.add(DailyBillListView(context))

        val adapter = BasePagerAdapter(context , list)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : IonPageChangeListener(){
            override fun onPageSelected(index: Int) {
                runOnUiThread {
                    item1TextView.setTextColor(if (0 == index){resources.getColor(R.color.c27) }else{resources.getColor(R.color.c13)})
                    item2TextView.setTextColor(if (1 == index){resources.getColor(R.color.c27) }else{resources.getColor(R.color.c13)})
                    item3TextView.setTextColor(if (2 == index){resources.getColor(R.color.c27) }else{resources.getColor(R.color.c13)})


                    list[index].freshByHand(true)
                }
            }
        })
    }

    fun onBtnClick(v : View){
        when(v){
            addConsumptionBtnTextView ->{
                startActivity(Intent(context , AddConsumptionActivity::class.java))
            }

            item1TextView ->{
                viewPager.setCurrentItem(0 ,false)
            }

            item2TextView ->{
                viewPager.setCurrentItem(1 ,false)
            }

            item3TextView ->{
                viewPager.setCurrentItem(2 ,false)
            }
        }
    }

}
