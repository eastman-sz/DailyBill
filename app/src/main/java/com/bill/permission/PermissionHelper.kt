package com.gym.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

class PermissionHelper {

    companion object {

        //需要的所有权限
        fun hasAllNeededPermissions(context: Context) : Boolean{
            return hasPermission(context , Manifest.permission.READ_EXTERNAL_STORAGE)
                    && hasPermission(context , Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        //请求需要的所有权限
        fun requestAllNeededPermissions(activity: Activity , requestCode : Int){
            val list = ArrayList<String>()
            list.addAll(getPermission(activity , Manifest.permission.READ_EXTERNAL_STORAGE))
            list.addAll(getPermission(activity , Manifest.permission.WRITE_EXTERNAL_STORAGE))
            if (list.isEmpty()){
                return
            }
            val permissions = list.toTypedArray()
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }

        private fun hasReadPhoneStatePermission(context: Context) : Boolean{
            return hasPermission(context , Manifest.permission.READ_PHONE_STATE)
        }

        private fun getPermission(context: Context , permission : String) : List<String>{
            val list = ArrayList<String>()
            if (hasPermission(context , permission)){
                return list
            }
            list.add(permission)
            return list
        }

        //是否有某种权限
        private fun hasPermission(context: Context , permission : String) : Boolean{
            val permission = ContextCompat.checkSelfPermission(context, permission)
            return permission == PackageManager.PERMISSION_GRANTED
        }

    }

}