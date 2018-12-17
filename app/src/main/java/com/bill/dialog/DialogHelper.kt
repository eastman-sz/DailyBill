package com.bill.dialog

import android.content.Context
import com.bill.daylist.DailyBillFilterActivity
import com.bill.daylist.ListenerConfig
import com.bill.daylist.OnDailyBillFilterParamSetListener
import org.jetbrains.anko.startActivity

class DialogHelper {

    companion object {

        fun showDateTimeSelectDialog(context: Context , timestamp: Long ,onDateTimeSelectedListener : DateTimeSelectDialog.OnDateTimeSelectedListener?){
            val dialog = DateTimeSelectDialog(context)
            dialog.show()
            dialog.setTimestamp(timestamp)
            dialog.onDateTimeSelectedListener = onDateTimeSelectedListener
        }

        fun showDailyBillFilter(context: Context , onDailyBillFilterParamSetListener : OnDailyBillFilterParamSetListener?){
            ListenerConfig.onDailyBillFilterParamSetListener = onDailyBillFilterParamSetListener
            context.startActivity<DailyBillFilterActivity>()
        }



    }


}