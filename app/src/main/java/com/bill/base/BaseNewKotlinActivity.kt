package com.bill.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

open class BaseNewKotlinActivity : AppCompatActivity() {

    var context : Context?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
    }

    open fun initActivity(){
        getIntentData()
        initTitle()
        initViews()
        initListener()
    }

    open fun getIntentData(){}

    open fun initTitle(){}

    open fun initViews(){}

    open fun initListener(){}


}
