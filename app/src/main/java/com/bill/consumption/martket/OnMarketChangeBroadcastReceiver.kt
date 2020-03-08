package com.bill.consumption.martket

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.bill.application.IApplication
import com.bill.util.BroadcastAction

class OnMarketChangeBroadcastReceiver : BroadcastReceiver{

    var onMarketChangeBroadcastReceiverListener : OnMarketChangeBroadcastReceiverListener ?= null

    private var hasRegistered = false

    constructor()

    fun register(){
        if (!hasRegistered){
            val filter = IntentFilter()
            filter.addAction(BroadcastAction.marketChanged)
            IApplication.context?.registerReceiver(this , filter)
            hasRegistered = true
        }
    }

    fun unRegister(){
        if (hasRegistered){
            IApplication.context?.unregisterReceiver(this)
            hasRegistered = false
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            BroadcastAction.marketChanged ->{
                onMarketChangeBroadcastReceiverListener?.onMarketChanged()
            }
        }

    }


}