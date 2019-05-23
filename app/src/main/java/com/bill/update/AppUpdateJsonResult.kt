package com.bill.update

import com.utils.lib.ss.net.HttpResponse

class AppUpdateJsonResult : HttpResponse() {

    var version : String ?= null
    var dl_url : String ?= null
    var description : String ?= null
    var enforce_update = 0




}