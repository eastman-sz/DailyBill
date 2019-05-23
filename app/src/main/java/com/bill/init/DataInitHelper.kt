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
                        list.add("美宜家")
                        list.add("肯德基")
                        list.add("益田假日")

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
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,100 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,101 , "早餐"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,102 , "中餐"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 1 ,103 , "晚餐"))
                        //衣服饰品
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,200 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,201 , "外套"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,202 , "内衣"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,203 , "裤子"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,204 , "鞋子"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,205 , "帽子"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,206 , "包包"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,207 , "美妆"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,208 , "护肤"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 2 ,209 , "美发"))
                        //居家物业
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,300 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,301 , "房贷"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,302 , "房租水电"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,303 , "酒店"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,304 , "日常用品"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,305 , "物业管理"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,306 , "维修保养"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 3 ,307 , "快递费用"))
                        //行车交通
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,400 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,401 , "公交"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,402 , "地铁"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,403 , "高铁"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,404 , "网约车"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,405 , "租车借车"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,406 , "飞机"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 4 ,407 , "火车"))
                        //休闲娱乐
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,500 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,501 , "KTV"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,502 , "酒吧"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,503 , "电影"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,504 , "戏剧"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,505 , "运动健身"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,506 , "休闲玩乐"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,507 , "宠物"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 5 ,508 , "旅游度假"))
                        //学习进修
                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,600 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,601 , "培训进修"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,602 , "数码装备"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 6 ,603 , "书报杂志"))
                        //医疗保健
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,700 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,701 , "住院"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,702 , "保健品"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,703 , "药品费"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 7 ,704 , "体检"))

                        //人情往来
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,800 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,801 , "红包支出"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,802 , "送礼请客"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,803 , "孝敬家长"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,804 , "还人钱物"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 8 ,805 , "慈善捐助"))
                        //金融保险
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,900 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,901 , "保险证券"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,902 , "投资亏损"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,903 , "按揭还款"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,904 , "消费税收"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,905 , "利息支出"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,906 , "赔偿罚款"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 9 ,907 , "银行手续"))
                        //其他杂项
                        smallTypeList.add(SmallType(SuperType.Expense.type , 10 ,1000 , "默认"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 10 ,1001 , "其他支出"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 10 ,1002 , "意外丢失"))
                        smallTypeList.add(SmallType(SuperType.Expense.type , 10 ,1003 , "烂账损失"))

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
                        //职业收入
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,10000 , "工资收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,10001 , "经营收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,10002 , "加班补贴"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,10003 , "奖金收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 1 ,10004 , "兼职收入"))
                        //投资收入
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 2 ,20000 , "利息收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 2 ,20001 , "平台福利"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 2 ,20002 , "投资分红"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 2 ,20003 , "股票收入"))
                        //其他收入
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 3 ,30000 , "红包收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 3 ,30001 , "中奖收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 3 ,30002 , "意外收入"))
                        incomeSmallTypeList.add(SmallType(SuperType.Income.type , 3 ,30003 , "父母给钱"))

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