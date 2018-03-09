package com.bill.bill

/**
 * Created by E on 2018/3/8.
 */
class BillRoad {

    //一个industryId下有多个marketId
    companion object {
        var industryId : Int = 0 //行业一级
        var industryName : Int = 0 //行业一级-名称
        var marketId : Int = 0 //细分二级
        var marketName : String = "" //细分二级-名称
    }

}