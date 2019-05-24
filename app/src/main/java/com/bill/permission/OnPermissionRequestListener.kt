package com.gym.permission
/**
 * 权限请求回调接口。
 * @author E
 */
interface OnPermissionRequestListener {

    fun onGranted()

    fun onDenied()

}