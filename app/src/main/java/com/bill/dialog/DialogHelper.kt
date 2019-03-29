package com.bill.dialog

import android.content.Context
import com.bill.consumption.type.BigType
import com.bill.consumption.type.OnConsumptionTypeSelectListener
import com.bill.consumption.type.TypeSelectDialog
import com.bill.consumption.type.add.AddSmallTypeDialog
import com.bill.consumption.type.add.BigTypeSelectDialog
import com.bill.consumption.type.add.EditSmallTypeNameDialog
import com.bill.daylist.DailyBillFilterActivity
import com.bill.daylist.ListenerConfig
import com.bill.daylist.OnDailyBillFilterParamSetListener
import com.common.dialog.CommonDialog
import com.common.dialog.OnCommonItemClickListener
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

        fun showCommonDialog(context: Context , content : String , leftBtnText : String , rightBtnText : String ,
                             onCommonItemClickListener : OnCommonItemClickListener<Int>){
            val dialog = CommonDialog(context)
            dialog.show()
            dialog.setParams(content , leftBtnText , rightBtnText , onCommonItemClickListener)
        }

        fun showTypeSelectDialog(context: Context , onConsumptionTypeSelectListener : OnConsumptionTypeSelectListener){
            val dialog = TypeSelectDialog(context)
            dialog.show()
            dialog.onConsumptionTypeSelectListener = onConsumptionTypeSelectListener
        }

        //选择一级分类
        fun showBigTypeSelectDialog(context: Context , onCommonItemClickListener : OnCommonItemClickListener<BigType> ?){
            val dialog = BigTypeSelectDialog(context)
            dialog.show()
            dialog.onCommonItemClickListener = onCommonItemClickListener
        }

        //添加二级分类
        fun showAddSmallTypeDialog(context: Context , bigTypeId : Int){
            val dialog = AddSmallTypeDialog(context)
            dialog.show()
            dialog.bigTypeId = bigTypeId
        }

        //修改二级分类名称
        fun showEditSmallTypeNameDialog(context: Context , smallTypeId : Int){
            val dialog = EditSmallTypeNameDialog(context)
            dialog.show()
            dialog.setSmallTypeId(smallTypeId)
        }

    }


}