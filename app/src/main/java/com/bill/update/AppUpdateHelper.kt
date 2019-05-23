package com.bill.update

import android.content.Context
import android.view.Gravity
import com.bill.dialog.DialogHelper
import com.common.dialog.OnCommonItemClickListener
import com.utils.lib.ss.common.ToastHelper
import java.io.File
/**
 * Check for update.
 * @author E
 */
class AppUpdateHelper {

    private var context : Context ?= null
    private var showToast = false

    constructor(context: Context){
        this.context = context
    }

    fun checkUpdate(showToast : Boolean){
        this.showToast = showToast

        AppUpdateNetHelper.checkUpdate(object : OnCommonNetListener<AppUpdateJsonResult>(){
            override fun onFailed(resultCode: Int) {
                if (showToast){
                    ToastHelper.makeText(context, "检查更新异常")
                }
            }
            override fun onResult(it: AppUpdateJsonResult) {
                parseAppUpdateJsonResult(it)
            }
        })
    }

    private fun parseAppUpdateJsonResult(it: AppUpdateJsonResult){
        val result = it.result
        if (0 == result){
            if (showToast){
                ToastHelper.makeText(context , "当前版本已是最新版本")
            }
            return
        }
        if (1 == result || 2 == result){
            //有版本更新
            val enforce_update = it.enforce_update //0有版本可更新 , 非强制 1,强制更新 2,禁止使用，退出
            when(enforce_update){
                0 -> showDialog(it)
            }
        }
    }

    private fun getFileName(filePath: String?): String {
        return if (null == filePath || "" == filePath) "" else filePath.substring(filePath.lastIndexOf(File.separator) + 1)
    }

    //非强制更新
    private fun showDialog(it: AppUpdateJsonResult){
        val description = it.description
        val content =  if (null == description || description.isEmpty()) "发现新版本，请立即升级更新" else description

        val dialog = DialogHelper.showCommonTitleDialog(context!! , "版本更新说明" , content , "下次提醒" , "立即升级" , object : OnCommonItemClickListener<Int>(){
            override fun onItemClick(item: Int) {
                if (1 == item){
                    //立即升级,下载文件
                    startDownLoad(it)
                }
            }
        })
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentGravity(Gravity.FILL_HORIZONTAL)
    }

    //start to download
    private fun startDownLoad(it: AppUpdateJsonResult){
        val dialog = UpdateProgressDialog(context!!)
        dialog.show()
        dialog.startDownload(it.dl_url , getFileName(it.dl_url) , object : OnDownloadListener(){
            override fun onStart() {
            }
            override fun onError() {
                ToastHelper.makeText(context , "文件下载异常")
            }
            override fun onSuccess(file: File) {
                installApk(file)
            }
        })
    }

    /**
     * 安装APK文件 //http://www.cnblogs.com/yongdaimi/p/6067319.html
     */
    private fun installApk(apkFile : File?) {
        if (null == apkFile) {
            return
        }
        AppInstaller.install(context!!, apkFile)
    }

}