package com.bill.update

import com.alibaba.fastjson.JSON
import com.bill.util.ILog
import com.bill.util.NetHelper2
import com.bill.util.PrefUtil
import com.bill.util.UrlPath
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.HashMap

class AppUpdateNetHelper {

    companion object {

        fun checkUpdate(onNetRequestListener : OnCommonNetListener<AppUpdateJsonResult>){
            doAsync {
                try {
                    val params = HashMap<String, Any>()
                    val jsonResult = NetHelper2.getInstance().sendRequest(params, UrlPath.APP_UPDATE_URLS)

                    ILog.e("------检查更新--------$jsonResult")

                    val appUpdateJsonResult = JSON.parseObject(jsonResult , AppUpdateJsonResult::class.java)

                    uiThread {
                        onNetRequestListener?.onResult(appUpdateJsonResult)
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                    uiThread {
                        onNetRequestListener?.onFailed(100)
                    }
                }
            }
        }

    }



}