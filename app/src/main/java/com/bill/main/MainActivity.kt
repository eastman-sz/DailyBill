package com.bill.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bill.bill.BillListActivity
import com.bill.consumption.AddConsumptionActivity
import com.common.base.BaseAppCompactActivitiy
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivitys()
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
