package com.bill.add

import android.os.Bundle
import com.bill.add.consumption.ConsumptionView
import com.bill.add.income.InComeView
import com.bill.base.BaseKotlinActivity
import com.bill.base.BaseKotlinRelativeLayout
import com.common.base.BasePagerAdapter
import com.common.base.OnIPageChangeListener
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_add_new_account.*
/**
 * Add new Account including consumption and income.
 * @author E
 */
class AddNewAccountActivity : BaseKotlinActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_account)
        initActivity()
    }

    override fun initViews() {
        val list = ArrayList<BaseKotlinRelativeLayout>()
        list.add(ConsumptionView(context))
        list.add(InComeView(context))

        val adapter = BasePagerAdapter<BaseKotlinRelativeLayout>(context , list)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : OnIPageChangeListener(){
            override fun onPageSelected(index: Int) {
                runOnUiThread {
                    addNewAccountHeaderView.onItemChange(index)
                }
            }
        })

        addNewAccountHeaderView.onCommonItemClickListener = object : OnCommonItemClickListener<Int>(){
            override fun onItemClick(it: Int) {
                runOnUiThread {
                    viewPager.setCurrentItem(it , false)
                }
            }
        }
    }

}
