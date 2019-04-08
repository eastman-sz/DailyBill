package com.bill.consumption

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.bill.application.IApplication
import com.bill.util.BroadcastAction
import com.bill.util.BroadcastHelper

/**
 * 添加一笔新的消费。
 * @author E
 */
class NewAddConsumptionBroadcastReceiveListener : BroadcastReceiver {

    var onNewAddConsumptionBroadcastReceiveListener : OnNewAddConsumptionBroadcastReceiveListener ?= null

    private var hasRegistered = false

    constructor(){}

    fun register(){
        if (hasRegistered){
            return
        }
        val filter = IntentFilter()
        filter.addAction(BroadcastAction.NEW_ADD_CONSUMPTION)
        filter.addAction(BroadcastAction.filterTimeRangeChanged)
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
            BroadcastAction.NEW_ADD_CONSUMPTION ->{
                onNewAddConsumptionBroadcastReceiveListener?.onNewAddConsumption()
            }

            BroadcastAction.filterTimeRangeChanged ->{
                val startTimestamp = intent.getLongExtra("startTimestamp" , 0L)
                val endTimestamp = intent.getLongExtra("endTimestamp" , 0L)

                onNewAddConsumptionBroadcastReceiveListener?.onFilterTimeRangeChanged(startTimestamp , endTimestamp)
            }
        }
    }



}