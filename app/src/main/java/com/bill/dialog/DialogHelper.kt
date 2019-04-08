package com.bill.dialog

import android.content.Context
import com.bill.billbook.BillBook
import com.bill.billbook.BillBookSelectDialog
import com.bill.consumption.martket.Market
import com.bill.consumption.martket.MarketSelectDialog
import com.bill.consumption.nature.NatureInfo
import com.bill.consumption.nature.NatureInfoSelectDialog
import com.bill.consumption.payment.Payment
import com.bill.consumption.payment.PaymentSelectDialog
import com.bill.consumption.type.BigType
import com.bill.consumption.type.OnConsumptionTypeSelectListener
import com.bill.consumption.type.TypeSelectDialog
import com.bill.consumption.type.add.AddSmallTypeDialog
import com.bill.consumption.type.add.BigTypeSelectDialog
import com.bill.consumption.type.add.EditBigTypeNameDialog
import com.bill.consumption.type.add.EditSmallTypeNameDialog
import com.bill.daylist.DailyBillFilterActivity
import com.bill.daylist.ListenerConfig
import com.bill.daylist.OnDailyBillFilterParamSetListener
import com.bill.summary.bar.filter.BarChartFilterActivity
import com.bill.summary.bar.filter.BarChartTypeSelectDialog
import com.bill.summary.bar.filter.OnBarChartFilterParamSetListener
import com.bill.wheelview.barChart.BarChart
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

        fun showBarChartFilter(context: Context , onBarChartFilterParamSetListener : OnBarChartFilterParamSetListener?){
            ListenerConfig.onBarChartFilterParamSetListener = onBarChartFilterParamSetListener
            context.startActivity<BarChartFilterActivity>()
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

        //修改一级分类名称
        fun showEditEditBigTypeNameDialog(context: Context , typeId : Int){
            val dialog = EditBigTypeNameDialog(context)
            dialog.show()
            dialog.setBigTypeId(typeId)
        }

        //消费性质
        fun showNatureInfoSelectDialog(context: Context , onCommonItemClickListener : OnCommonItemClickListener<NatureInfo> ?){
            val dialog = NatureInfoSelectDialog(context)
            dialog.show()
            dialog.onCommonItemClickListener = onCommonItemClickListener
        }

        //帐本选择
        fun showBillBookSelectDialog(context: Context , onCommonItemClickListener : OnCommonItemClickListener<BillBook>?){
            val dialog = BillBookSelectDialog(context)
            dialog.show()
            dialog.onCommonItemClickListener = onCommonItemClickListener
        }

        //商场选择
        fun showMarketSelectDialog(context: Context , onCommonItemClickListener : OnCommonItemClickListener<Market>?){
            val dialog = MarketSelectDialog(context)
            dialog.show()
            dialog.onCommonItemClickListener = onCommonItemClickListener
        }

        //支付方式
        fun showPaymentSelectDialog(context: Context , onCommonItemClickListener : OnCommonItemClickListener<Payment> ?){
            val dialog = PaymentSelectDialog(context)
            dialog.show()
            dialog.onCommonItemClickListener = onCommonItemClickListener
        }

        //条形图要展示的数据的类型选择
        fun showBarChartTypeSelectDialog(context: Context , onCommonItemClickListener : OnCommonItemClickListener<BarChart> ?){
            val dialog = BarChartTypeSelectDialog(context)
            dialog.show()
            dialog.onCommonItemClickListener = onCommonItemClickListener
        }

    }


}