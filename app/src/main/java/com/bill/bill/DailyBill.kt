package com.bill.bill

class DailyBill {

    var bid = 0L //相当于某一笔记帐的流水号
    var bookId = 0
    var amount = 0F
    var billTime = 0L
    var cTime = 0L
    var remarks : String ?= null
    var marketId = 0 //地点，网店都可以

    var bigTypeId = 0 //一级分类ID
    var smallTypeId = 0 //二级分类ID
    var natureId = 0 //性质ID
    var paymentId = 0 //支付方式

    var superType = 0 //超级大类 0代表支出 1代表收入

    var dayAmount = 0f
    var weekAmount = 0f
    var monthAmount = 0f
    var yearAmount = 0f

}