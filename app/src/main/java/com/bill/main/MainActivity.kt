package com.bill.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.bill.add.AddNewAccountActivity
import com.bill.base.BaseKotlinActivity
import com.bill.billbook.BillBookView
import com.bill.daylist.DailyBillListView
import com.bill.summary.SummaryView
import com.common.base.BasePagerAdapter
import com.common.base.BaseRelativeLayout
import com.common.base.OnIPageChangeListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
/**
 * Main Page.
 * @author E
 */
class MainActivity : BaseKotlinActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivity()
    }

    override fun initViews() {
        val list = ArrayList<BaseRelativeLayout>()
        list.add(BillBookView(context))
        list.add(SummaryView(context))
        list.add(DailyBillListView(context))

        val adapter = BasePagerAdapter(context , list)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : OnIPageChangeListener(){
            override fun onPageSelected(index: Int) {
                runOnUiThread {
                    item1TextView.setTextColor(if (0 == index){ContextCompat.getColor(context ,R.color.c27)}else{ContextCompat.getColor(context ,R.color.c13)})
                    item2TextView.setTextColor(if (1 == index){ContextCompat.getColor(context ,R.color.c27) }else{ContextCompat.getColor(context ,R.color.c13)})
                    item3TextView.setTextColor(if (2 == index){ContextCompat.getColor(context ,R.color.c27)}else{ContextCompat.getColor(context ,R.color.c13)})


                    list[index].freshByHand(true)
                }
            }
        })
    }

    fun onBtnClick(v : View){
        when(v){
            addConsumptionBtnTextView ->{
//                startActivity(Intent(context , AddConsumptionActivity::class.java))
                startActivity<AddNewAccountActivity>()
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
