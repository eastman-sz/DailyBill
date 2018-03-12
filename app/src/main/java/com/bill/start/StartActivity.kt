package com.bill.start

import android.content.Intent
import android.os.Bundle
import com.bill.init.DataInitHelper
import com.bill.main.MainActivity
import com.common.base.BaseAppCompactActivitiy
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        initActivitys()

        DataInitHelper.initData()

        bgLayout.postDelayed({
            runOnUiThread({
                startActivity(Intent(context , MainActivity::class.java))
            })
        } , 500)
    }

}
