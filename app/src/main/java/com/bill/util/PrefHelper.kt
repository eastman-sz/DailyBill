package com.bill.util

class PrefHelper {

    companion object{

        fun initPointNames() {
            PrefUtil.instance().setIntPref(Prefkey.ADD_POINT, 1)
        }

        fun initedPointNames(): Boolean {
            return 1 == PrefUtil.instance().getIntPref(Prefkey.ADD_POINT, 0)
        }

        //最后一次更新App的时间
        fun updateLastAppUpdateTimestamp() {
            PrefUtil.instance().setLongPref(Prefkey.LastUpdateAppTime, System.currentTimeMillis() / 1000)
        }

        fun isUpdateExpired(): Boolean {
            val ctime = System.currentTimeMillis() / 1000
            val lastUpdateTime = PrefUtil.instance().getLongPref(Prefkey.LastUpdateAppTime, ctime)
            val diff = Math.abs(ctime - lastUpdateTime)
            //超过一天时设过过期//86400
            ILog.e("=======过期时间=================:: $diff")

            return diff >= 86400
        }

    }

}