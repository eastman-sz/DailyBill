package com.bill.util

import android.content.Intent
import com.bill.application.IApplication

class BroadcastHelper {

    companion object {

        private val filterTimeRangeIntent = Intent(BroadcastAction.filterTimeRangeChanged)

        fun onFilterTimeRangeChanged(startTimestamp : Long , endTimestamp : Long){
            filterTimeRangeIntent.putExtra("startTimestamp" , startTimestamp)
            filterTimeRangeIntent.putExtra("endTimestamp" , endTimestamp)
            sendBroadcast(filterTimeRangeIntent)
        }

        fun sendBroadcast(intent: Intent){
            IApplication.context?.sendBroadcast(intent)
        }

    }

}