package com.gym.permission

import android.content.Context
import com.bill.permission.AllNeededPermissionRequestActivity
import org.jetbrains.anko.startActivity

class PermissionRequestHelper {

    companion object {

        fun requestAllNeededPermissions(context: Context , onAllNeededPermissionRequestListener : OnPermissionRequestListener?){
            val hasAllNeededPermissions = PermissionHelper.hasAllNeededPermissions(context)
            if (hasAllNeededPermissions){
                onAllNeededPermissionRequestListener?.onGranted()
                return
            }
            PermissionConfig.onAllNeededPermissionRequestListener = onAllNeededPermissionRequestListener
            context.startActivity<AllNeededPermissionRequestActivity>()
        }

    }

}