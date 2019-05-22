package com.bill.init

import com.bill.billbook.BillBookDbHelper
import com.bill.consumption.martket.MarketDbHelper
import com.bill.consumption.nature.NatureInfo
import com.bill.consumption.nature.NatureInfoDbHelper
import com.bill.consumption.payment.PaymentDbHelper
import com.bill.consumption.type.*
import com.bill.util.PrefHelper
import org.jetbrains.anko.doAsync
import java.lang.Exception
/**
 * Created by E on 2018/3/12.
 */
class DataInitHelper {

    companion object {
        open fun initData(){
            doAsync {
                //add ConsumptionPoint
                try {
                    val initedPointNames = PrefHelper.initedPointNames()
                    if (!initedPointNames){
                        PrefHelper.initPointNames()

                        val list = ArrayList<String>()
                        list.add("沃尔玛")
                        list.add("京东")
                        list.add("天虹")
                        list.add("滴滴")
                        list.add("唯品会")
                        list.add("淘宝")

                        list.forEach {
                            MarketDbHelper.add(it)
                        }

                        //add default billBook
                        BillBookDbHelper.saveDefault("默认")

                        //add Expense bigType
                        val bigTypeList = ArrayList<BigType>()
                        bigTypeList.add(BigType(SuperType.Expense.type , 1 , "吃"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 2 , "穿"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 3 , "住"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 4 , "行"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 5 , "娱"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 6 , "学"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 7 , "医"))

                        bigTypeList.forEach {
                            BigTypeDbHelper.save(it.superType, it.typeId , it.typeName!!)
                        }

                        //add Expense smallType
                        val smallTypeList = ArrayList<SmallType>()
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,10 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,11 , "早餐"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,12 , "中餐"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,13 , "晚餐"))

                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,20 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,21 , "外套"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,22 , "内衣"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,23 , "裤子"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,24 , "鞋子"))

                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,30 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,31 , "房贷"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,32 , "房租"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,33 , "酒店"))

                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,40 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,41 , "公交"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,42 , "地铁"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,43 , "高铁"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,44 , "网约车"))

                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,50 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,51 , "KTV"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,52 , "酒吧"))

                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,60 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,61 , "进修"))

                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,70 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,71 , "住院"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,72 , "保健品"))

                        smallTypeList.forEach {
                            SmallTypeDbHelper.save(it.superType, it.bigTypeId , it.typeId , it.typeName!!)
                        }

                        //add Income bigType
                        val incomeBigTypeList = ArrayList<BigType>()
                        incomeBigTypeList.add(BigType(SuperType.Income.type , 1 , "职业收入"))
                        incomeBigTypeList.add(BigType(SuperType.Income.type , 2 , "投资收入"))
                        incomeBigTypeList.add(BigType(SuperType.Income.type , 3 , "其他收入"))

                        incomeBigTypeList.forEach {
                            BigTypeDbHelper.save(it.superType, it.typeId , it.typeName!!)
                        }

                        //add Income smallType
                        val incomeSmallTypeList = ArrayList<SmallType>()
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,10 , "工资收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,11 , "经营收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,12 , "加班补贴"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,13 , "奖金收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,14 , "兼职收入"))

                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 2 ,20 , "利息收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 2 ,21 , "平台福利"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 2 ,22 , "投资分红"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 2 ,23 , "股票收入"))

                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 3 ,30 , "红包收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 3 ,31 , "中奖收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 3 ,32 , "意外收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 3 ,33 , "父母给钱"))

                        incomeSmallTypeList.forEach {
                            SmallTypeDbHelper.save(it.superType, it.bigTypeId , it.typeId , it.typeName!!)
                        }


                        //--消费性质================================================
                        val natureList = ArrayList<NatureInfo>()
                        natureList.add(NatureInfo("日常消费"))
                        natureList.add(NatureInfo("固定支出"))

                        natureList.forEach {

                            NatureInfoDbHelper.save(it.natureName!!)
                        }

                        //支付方式
                        val paymentList = ArrayList<String>()
                        paymentList.add("现金")
                        paymentList.add("支付宝")
                        paymentList.add("微信")
                        paymentList.add("信用卡")
                        paymentList.add("储蓄卡")

                        paymentList.forEach {
                            PaymentDbHelper.save(it)
                        }
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }

                //next

            }
        }
    }


}