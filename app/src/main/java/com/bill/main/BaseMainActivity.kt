package com.bill.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bill.base.BaseNewKotlinActivity
import com.bill.update.AppUpdateHelper
import com.gym.permission.OnPermissionRequestListener
import com.gym.permission.PermissionRequestHelper

open class BaseMainActivity : BaseNewKotlinActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        moveTaskToBack(true)
    }
}