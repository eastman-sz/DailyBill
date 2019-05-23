package com.bill.bill

import com.bill.base.OnCommonRequestListener
import com.bill.daylist.DailyBillFilter
import com.utils.lib.ss.common.DateHelper
import com.utils.lib.ss.common.MathUtil
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.math.BigDecimal

/**
 * Helper class for fetch special daily bill data.
 * @author E
 */
class DailyBillDataFetchHelper {

    companion object {

        //所有记帐数据
        fun getAllDailyBills(onCommonRequestListener : OnCommonRequestListener<List<BillList>>?){
            doAsync {
                val dailyBillList = DailyBillDbHelper.getAllDailyBills()
                uiThread {
                    getGroupedBillList(dailyBillList , onCommonRequestListener)
                }
            }
        }

        //某一个帐户下的所有数据
        fun getAllDailyBills(bookId : Long , onCommonRequestListener : OnCommonRequestListener<List<BillList>>?){
            doAsync {
                val dailyBillList = DailyBillDbHelper.getAllDailyBills(bookId)
                uiThread {
                    getGroupedBillList(dailyBillList , onCommonRequestListener)
                }
            }
        }

        //某个过滤条件下的所有记帐数据
        fun getAllDailyBills(dailyBillFilter: DailyBillFilter , onCommonRequestListener : OnCommonRequestListener<List<BillList>>?){
            doAsync {
                val dailyBillList = DailyBillDbHelper.getFilterDailyBill(dailyBillFilter)
                uiThread {
                    getGroupedBillList(dailyBillList , onCommonRequestListener)
                }
            }
        }

        private fun getGroupedBillList(dailyBills : List<DailyBill> , onCommonRequestListener : OnCommonRequestListener<List<BillList>>?){
            val list = ArrayList<BillList>()
            if (dailyBills.isEmpty()){
                onCommonRequestListener?.onSuccess(list)
            }

            //每月总额
            val monthAmountMap = HashMap<String , BigDecimal>()

            doAsync {
                dailyBills.forEach {
                    val billList = BillList.fromBill(it)

                    list.add(billList)

                    val mmOfYear = DateHelper.timestampFormat(billList.billtime , "yyyy-MM")
                    val amount = billList.amount

                    val containsKey = monthAmountMap.containsKey(mmOfYear)
                    when(containsKey){
                        true -> {
                            val monthAmount = monthAmountMap[mmOfYear]
                            monthAmountMap.put(mmOfYear , MathUtil.addBigDecimal(monthAmount!! , amount , 2))
                        }

                        false ->{
                            monthAmountMap[mmOfYear] = amount
                        }
                    }
                }

                list.forEach {
                    val mmOfYear = DateHelper.timestampFormat(it.billtime , "yyyy-MM")

                    it.monthAmount = monthAmountMap[mmOfYear]!!

                }

                list.sort()

                uiThread {

                    onCommonRequestListener?.onSuccess(list)
                }

            }

        }



    }


}