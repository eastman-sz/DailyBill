package com.bill.consumption

open class OnNewAddConsumptionBroadcastReceiveListener {

    open fun onNewAddConsumption(){}

    open fun onFilterTimeRangeChanged(startTimestamp : Long, endTimestamp : Long){}
}