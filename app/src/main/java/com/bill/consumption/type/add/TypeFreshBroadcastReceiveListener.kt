package com.bill.consumption.type.add

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.bill.application.IApplication
import com.bill.util.BroadcastAction
/**
 * 一二级分类变化。
 * @author E
 */
class TypeFreshBroadcastReceiveListener : BroadcastReceiver {

    var onTypeFreshBroadcastReceiveListener : OnTypeFreshBroadcastReceiveListener ?= null

    private var hasRegistered = false

    constructor(){
        register()
    }

    private fun register(){
        if (hasRegistered){
            return
        }
        val filter = IntentFilter()
        filter.addAction(BroadcastAction.smallTypeFresh)
        IApplication.context?.registerReceiver(this , filter)
        hasRegistered = true
    }

    fun unRegister(){
        if (hasRegistered){
            IApplication.context?.unregisterReceiver(this)
            hasRegistered = false
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        when(action){
            BroadcastAction.smallTypeFresh ->{
                onTypeFreshBroadcastReceiveListener?.onSmallTypeFresh()
            }
        }

    }
}