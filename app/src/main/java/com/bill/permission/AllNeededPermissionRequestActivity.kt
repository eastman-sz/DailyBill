package com.bill.permission

import android.app.Activity
import android.os.Bundle
import com.gym.permission.PermissionConfig
import com.gym.permission.PermissionHelper

class AllNeededPermissionRequestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hasAccessAllPermissions(true)
    }

    private fun hasAccessAllPermissions(continueRequestPermission : Boolean){
        when(PermissionHelper.hasAllNeededPermissions(this)){
            true ->{
                finish()
                PermissionConfig.onAllNeededPermissionRequestListener?.onGranted()
            }
            false ->{
                if (continueRequestPermission){
                    PermissionHelper.requestAllNeededPermissions(this , 1478)
                }else{
                    finish()
                    PermissionConfig.onAllNeededPermissionRequestListener?.onDenied()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when(requestCode){
            1478 ->{
                hasAccessAllPermissions(false)
            }
        }
    }
}
