package com.bill.dialog

import android.content.Context
import com.bill.consumption.type.TypeSelectDialog
import com.bill.daylist.DailyBillFilterActivity
import com.bill.daylist.ListenerConfig
import com.bill.daylist.OnDailyBillFilterParamSetListener
import com.common.dialog.CommonDialog
import com.common.dialog.OnCommonDialogBtnClickListener
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

        fun showCommonDialog(context: Context , title : String , content : String , leftBtnText : String , rightBtnText : String ,
                             onCommonItemClickListener : OnCommonItemClickListener<Int>){
            val dialog = CommonDialog(context)
            dialog.show()
            dialog.setDialogText(title , content , leftBtnText, rightBtnText)
            dialog.setOnCommonDialogBtnClickListener(object : OnCommonDialogBtnClickListener{
                override fun onLeftBtnClik() {
                    onCommonItemClickListener?.onItemClick(0)
                }
                override fun onRightBtnClik() {
                    onCommonItemClickListener?.onItemClick(1)
                }
            })
        }

        fun showTypeSelectDialog(context: Context){
            val dialog = TypeSelectDialog(context)
            dialog.show()
        }



    }


}