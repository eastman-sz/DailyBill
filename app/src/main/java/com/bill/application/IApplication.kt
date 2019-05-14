package com.bill.application

import android.app.Application
import android.content.Context
import com.bill.google.MobileAdsHelper
import com.bill.umeng.UMConstant
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

class IApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        context = this

        //init ument
        UMConfigure.init(context, UMConstant.AppKey, "10000", UMConfigure.DEVICE_TYPE_PHONE, null)
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL)

        MobileAdsHelper.init(this)
    }

    companion object {

        var context : Context ?= null


    }

}