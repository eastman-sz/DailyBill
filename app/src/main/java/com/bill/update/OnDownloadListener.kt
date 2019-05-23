package com.bill.update

import java.io.File

open class OnDownloadListener {

    open fun onStart(){}

    open fun onError(){}

    open fun onProgress(progress : Int){}

    open fun onSuccess(file : File){}

}