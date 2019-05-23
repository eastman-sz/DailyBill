package com.bill.update

import android.content.Context
import android.os.Bundle
import com.common.dialog.BaseDialog
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.softupdate_progress.*
import java.io.File
/**
 * Download progress dialog.
 * @author E
 */
class UpdateProgressDialog : BaseDialog {

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.softupdate_progress)

        init()
    }

    override fun initViews() {
        setMaxProgress(100)
    }

    fun setProgress(progress: Int) {
        numberProgressBar.setProgress(progress)
    }

    fun setMaxProgress(maxProgress: Int) {
        numberProgressBar.setMax(maxProgress)
    }

    fun startDownload(downloadPath : String?, fileName : String? , onDownloadListener : OnDownloadListener?){
        val appDownloadManager = AppDownloadManager()
        appDownloadManager.setParams(downloadPath , fileName , object : OnDownloadListener(){
            override fun onError() {
                dismiss()
                onDownloadListener?.onError()
            }
            override fun onProgress(progress: Int) {
                onDownloadListener?.onProgress(progress)
                setProgress(progress)
            }
            override fun onStart() {
                onDownloadListener?.onStart()
            }
            override fun onSuccess(file: File) {
                dismiss()
                onDownloadListener?.onSuccess(file)
            }
        })
        appDownloadManager.startDownLoad()
    }

}