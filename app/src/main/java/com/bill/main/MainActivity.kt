package com.bill.main

import android.os.Bundle
import com.common.base.BaseAppCompactActivitiy
import com.sz.kk.daily.bill.R

class MainActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivitys()
    }




}
