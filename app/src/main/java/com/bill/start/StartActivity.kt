package com.bill.start

import android.os.Bundle
import com.bill.base.BaseKotlinActivity
import com.bill.init.DataInitHelper
import com.bill.main.MainActivity
import com.sz.kk.daily.bill.R
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_start.*
import org.jetbrains.anko.startActivity

class StartActivity : BaseKotlinActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        initActivity()

        DataInitHelper.initData()

        bgLayout.postDelayed({
            runOnUiThread {
                startActivity<MainActivity>()
                finish()
            }
        } , 200)
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart(context.javaClass.simpleName)
        MobclickAgent.onResume(this)

    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(context.javaClass.simpleName)
        MobclickAgent.onPause(this)
    }
}
