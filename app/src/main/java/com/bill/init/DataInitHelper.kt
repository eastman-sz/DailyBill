package com.bill.init

import com.bill.billbook.BillbookDbHelper
import com.bill.point.ConsumptionPointDbHelper
import com.bill.util.PrefHelper
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
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

                        list.forEach({
                            ConsumptionPointDbHelper.add(it)
                        })

                        //add default billBook
                        BillbookDbHelper.saveDefault("默认")
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }

                //next

            }
        }
    }


}