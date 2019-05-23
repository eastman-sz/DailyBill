package com.bill.update

import android.os.Handler
import android.os.Looper
import com.bill.util.ILog
import com.bill.util.PrefHelper
import com.smilefuns.breakpoint.bean.SdkSQLDownLoadInfo
import com.smilefuns.breakpoint.downloader.SdkDownLoadListener
import com.smilefuns.breakpoint.downloader.SdkDownLoadManagerHelper
import java.io.File

class AppDownloadManager {

    private var downloadPath : String ?= null
    private var fileName : String ?= null
    private var onDownloadListener : OnDownloadListener ?= null

    private val taskId = "101"
    private var firstErrorTime = 0L

    //下载异常
    private val downLoadError = 3

    private val handler = Handler(Looper.getMainLooper()){
        when(it.what){
            downLoadError ->{
                //下载错误
                val cTime = System.currentTimeMillis() / 1000
                if (0L == firstErrorTime) {
                    firstErrorTime = cTime
                }
                //一定时间内可以继续请求下载
                val differ = Math.abs(cTime - firstErrorTime)
                if (differ < 120) {
                    SdkDownLoadManagerHelper.getInstance().downLoadManager.startTask(taskId)
                } else {
                    //返回下载异常
                    onDownloadListener?.onError()
                }
            }
        }
        true
    }

    fun setParams(downloadPath : String?, fileName : String? , onDownloadListener : OnDownloadListener?){
        this.downloadPath = downloadPath
        this.fileName = fileName
        this.onDownloadListener = onDownloadListener
    }

    fun startDownLoad(){
        if (null == downloadPath || null == fileName){
            handler.post {
                onDownloadListener?.onError()
            }
            return
        }
        if (downloadPath!!.isNullOrEmpty() || fileName!!.isNullOrEmpty()){
            handler.post {
                onDownloadListener?.onError()
            }
            return
        }

        val isUpdateExpired = PrefHelper.isUpdateExpired()
        if (isUpdateExpired) {
            SdkDownLoadManagerHelper.getInstance().clearAllDownloadedFiles()
        }
        PrefHelper.updateLastAppUpdateTimestamp()

        val exist = SdkDownLoadManagerHelper.getInstance().fileExist(taskId, fileName)
        if (exist){
            val file = SdkDownLoadManagerHelper.getInstance().getExistFile(taskId, fileName)
            if (null != file){
                //文件已存在，返回
                handler.post {
                    onDownloadListener?.onSuccess(file)
                }
            }
            return
        }

        ILog.e("ilog", "=====下载地址==: $downloadPath    文件名: $fileName")

        val sdkDownLoadManager = SdkDownLoadManagerHelper.getInstance().downLoadManager
        sdkDownLoadManager.setSupportBreakpoint(true)
        sdkDownLoadManager.addTask(taskId, downloadPath, fileName)

        sdkDownLoadManager.setSingleTaskListener(taskId , object : SdkDownLoadListener{
            override fun onStart(sdkSQLDownLoadInfo: SdkSQLDownLoadInfo) {
                handler.post {
                    onDownloadListener?.onStart()
                }
            }

            override fun onProgress(it: SdkSQLDownLoadInfo, b: Boolean) {
                val progress = (it.downloadSize * 100 / it.fileSize).toInt()
                firstErrorTime = 0

                handler.post {
                    onDownloadListener?.onProgress(progress)
                }
            }
            override fun onStop(sdkSQLDownLoadInfo: SdkSQLDownLoadInfo, b: Boolean) {

            }
            override fun onError(sdkSQLDownLoadInfo: SdkSQLDownLoadInfo) {
                handler.removeMessages(downLoadError)
                handler.sendEmptyMessageDelayed(downLoadError , 2000)
            }

            override fun onSuccess(it: SdkSQLDownLoadInfo) {
                firstErrorTime = 0
                val file = File(it.filePath);

                //回调，下载成功
                handler.post {
                    onDownloadListener?.onSuccess(file)
                }

            }
        })
        sdkDownLoadManager.startTask(taskId)
    }

}