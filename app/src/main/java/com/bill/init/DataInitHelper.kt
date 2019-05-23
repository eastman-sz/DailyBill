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
                        bigTypeList.add(BigType(SuperType.Expense.type , 1 , "食品酒水"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 2 , "衣服饰品"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 3 , "居家物业"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 4 , "行车交通"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 5 , "休闲娱乐"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 6 , "学习进修"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 7 , "医疗保健"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 8 , "人情往来"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 9 , "金融保险"))
                        bigTypeList.add(BigType(SuperType.Expense.type , 10 , "其他杂项"))

                        bigTypeList.forEach {
                            BigTypeDbHelper.save(it.superType, it.typeId , it.typeName!!)
                        }

                        //add Expense smallType
                        val smallTypeList = ArrayList<SmallType>()
                        //食品酒水
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,10 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,11 , "早餐"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,12 , "中餐"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,13 , "晚餐"))
                        //衣服饰品
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,20 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,21 , "外套"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,22 , "内衣"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,23 , "裤子"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,24 , "鞋子"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,25 , "帽子"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,26 , "包包"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,27 , "美妆"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,28 , "护肤"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,29 , "美发"))
                        //居家物业
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,30 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,31 , "房贷"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,32 , "房租水电"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,33 , "酒店"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,34 , "日常用品"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,35 , "物业管理"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,36 , "维修保养"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,37 , "快递费用"))
                        //行车交通
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,40 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,41 , "公交"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,42 , "地铁"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,43 , "高铁"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,44 , "网约车"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,45 , "租车借车"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,46 , "飞机"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,47 , "火车"))
                        //休闲娱乐
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,50 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,51 , "KTV"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,52 , "酒吧"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,53 , "电影"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,54 , "戏剧"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,55 , "运动健身"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,56 , "休闲玩乐"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,57 , "宠物"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,58 , "旅游度假"))
                        //学习进修
                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,60 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,61 , "培训进修"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,62 , "数码装备"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,63 , "书报杂志"))
                        //医疗保健
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,70 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,71 , "住院"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,72 , "保健品"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,73 , "药品费"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,74 , "体检"))

                        //人情往来
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,80 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,81 , "红包支出"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,82 , "送礼请客"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,83 , "孝敬家长"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,84 , "还人钱物"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,85 , "慈善捐助"))
                        //金融保险
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,90 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,91 , "保险证券"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,92 , "投资亏损"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,93 , "按揭还款"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,94 , "消费税收"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,95 , "利息支出"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,96 , "赔偿罚款"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,97 , "银行手续"))
                        //其他杂项
                        smallTypeList.add(SmallType(SuperType.Expense.type , 10 ,100 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 10 ,101 , "其他支出"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 10 ,102 , "意外丢失"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 10 ,103 , "烂账损失"))

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