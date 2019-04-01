package com.bill.init

import com.bill.billbook.BillBookDbHelper
import com.bill.consumption.martket.MarketDbHelper
import com.bill.consumption.nature.NatureInfo
import com.bill.consumption.nature.NatureInfoDbHelper
import com.bill.consumption.type.BigType
import com.bill.consumption.type.BigTypeDbHelper
import com.bill.consumption.type.SmallType
import com.bill.consumption.type.SmallTypeDbHelper
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

                        //add bigType
                        val bigTypeList = ArrayList<BigType>()
                        bigTypeList.add(BigType(1 , "吃"))
                        bigTypeList.add(BigType(2 , "穿"))
                        bigTypeList.add(BigType(3 , "住"))
                        bigTypeList.add(BigType(4 , "行"))
                        bigTypeList.add(BigType(5 , "娱"))
                        bigTypeList.add(BigType(6 , "学"))
                        bigTypeList.add(BigType(7 , "医"))

                        bigTypeList.forEach {
                            BigTypeDbHelper.save(it.typeId , it.typeName!!)
                        }

                        //add smallType
                        val smallTypeList = ArrayList<SmallType>()
                        smallTypeList.add(SmallType(1 ,10 , "默认"))
                        smallTypeList.add(SmallType(1 ,11 , "早餐"))
                        smallTypeList.add(SmallType(1 ,12 , "中餐"))
                        smallTypeList.add(SmallType(1 ,13 , "晚餐"))

                        smallTypeList.add(SmallType(2 ,20 , "默认"))
                        smallTypeList.add(SmallType(2 ,21 , "穿"))

                        smallTypeList.add(SmallType(3 ,30 , "默认"))
                        smallTypeList.add(SmallType(3 ,31 , "住"))

                        smallTypeList.add(SmallType(4 ,40 , "默认"))
                        smallTypeList.add(SmallType(4 ,41 , "公交"))
                        smallTypeList.add(SmallType(4 ,42 , "地铁"))
                        smallTypeList.add(SmallType(4 ,43 , "高铁"))
                        smallTypeList.add(SmallType(4 ,44 , "网约车"))

                        smallTypeList.add(SmallType(5 ,50 , "默认"))
                        smallTypeList.add(SmallType(5 ,51 , "娱"))

                        smallTypeList.add(SmallType(6 ,60 , "默认"))
                        smallTypeList.add(SmallType(6 ,61 , "学"))

                        smallTypeList.add(SmallType(7 ,70 , "默认"))
                        smallTypeList.add(SmallType(7 ,71 , "医"))

                        smallTypeList.forEach {
                            SmallTypeDbHelper.save(it.bigTypeId , it.typeId , it.typeName!!)
                        }


                        //--消费性质================================================
                        val natureList = ArrayList<NatureInfo>()
                        natureList.add(NatureInfo("日常消费"))
                        natureList.add(NatureInfo("固定支出"))

                        natureList.forEach {
                            NatureInfoDbHelper.save(it.natureName!!)
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