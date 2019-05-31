package com.bill.bill

import com.bill.base.OnCommonRequestListener
import com.bill.consumption.type.SuperType
import com.bill.daylist.DailyBillFilter
import com.bill.util.ILog
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

        //一段时期内某一小类的明细
        fun getSmallTypeBillList(superType : Int , smallTypeId : Int , startTimestamp : Long , endTimestamp : Long , onCommonRequestListener : OnCommonRequestListener<List<BillList>>?){
            doAsync {
                val dailyBillList = DailyBillDbHelper.getSmallTypeDetails(superType , smallTypeId , startTimestamp , endTimestamp)
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
            val monthExpanseAmountMap = HashMap<String , BigDecimal>()
            val monthIncomeAmountMap = HashMap<String , BigDecimal>()

            doAsync {
                dailyBills.forEach {
                    val billList = BillList.fromBill(it)

                    list.add(billList)

                    val mmOfYear = DateHelper.timestampFormat(billList.billtime , "yyyy-MM")
                    val amount = billList.amount
                    val superType = billList.superType

                    ILog.e("===================superType==================: $superType")

                    when(superType){
                        SuperType.Expense.type ->{
                            //支出
                            val containsKey = monthExpanseAmountMap.containsKey(mmOfYear)
                            when(containsKey){
                                true ->{
                                    val monthAmount = monthExpanseAmountMap[mmOfYear]
                                    monthExpanseAmountMap.put(mmOfYear , MathUtil.addBigDecimal(monthAmount , amount , 2))
                                }
                                false ->{
                                    monthExpanseAmountMap.put(mmOfYear , amount)
                                }
                            }
                        }

                        SuperType.Income.type ->{
                            //收入
                            val containsKey = monthIncomeAmountMap.containsKey(mmOfYear)
                            when(containsKey){
                                true ->{
                                    val monthAmount = monthIncomeAmountMap[mmOfYear]
                                    monthIncomeAmountMap.put(mmOfYear , MathUtil.addBigDecimal(monthAmount!! , amount , 2))
                                }

                                false ->{
                                    monthIncomeAmountMap.put(mmOfYear , amount)
                                }
                            }
                        }
                    }
                }

                list.forEach {
                    try {
                        val mmOfYear = DateHelper.timestampFormat(it.billtime , "yyyy-MM")

                        it.monthExpanseAmount = if (monthExpanseAmountMap.containsKey(mmOfYear)) monthExpanseAmountMap[mmOfYear] else BigDecimal(0)
                        it.monthIncomeAmount = if (monthIncomeAmountMap.containsKey(mmOfYear)) monthIncomeAmountMap[mmOfYear] else BigDecimal(0)
                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                }

                list.sort()

                uiThread {

                    onCommonRequestListener?.onSuccess(list)
                }

            }

        }



    }


}