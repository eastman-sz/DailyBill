package com.bill.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bill.base.BaseNewKotlinActivity
import com.bill.google.OnGAdListener
import com.bill.google.interstitial.GInterstitialAdPatternA
import com.bill.update.AppUpdateHelper
import com.bill.util.PrefHelper
import com.gym.permission.OnPermissionRequestListener
import com.gym.permission.PermissionRequestHelper

open class BaseMainActivity : BaseNewKotlinActivity() {

    private val gInterstitialAdPatternA = GInterstitialAdPatternA()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gInterstitialAdPatternA.startLoad(this)
        gInterstitialAdPatternA.onGAdListener = object : OnGAdListener(){
            override fun onClosed() {
                moveTaskToBack(true)
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            checkUpdate()
        } , 500)
    }

    private fun checkUpdate(){
        PermissionRequestHelper.requestAllNeededPermissions(this , object : OnPermissionRequestListener{
            override fun onDenied() {
            }
            override fun onGranted() {
                AppUpdateHelper(context!!).checkUpdate(false)
            }
        })
    }

    override fun onBackPressed() {
        val count = PrefHelper.getMainBackPressCount()
        if (count > 2){
            gInterstitialAdPatternA.show()
        }else{
            moveTaskToBack(true)
        }
    }
}