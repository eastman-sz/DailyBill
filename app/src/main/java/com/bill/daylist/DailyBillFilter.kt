package com.bill.daylist

import com.bill.consumption.type.SuperType

/**
 * 过滤的数据。
 * @author E
 */
class DailyBillFilter {

    var startTimestamp = 0L
    var endTimestamp = 0L

    var bookId = 0
    var marketId = 0
    var natureId = 0
    var paymentId = 0

    var smallTypeId = 0
    var bigTypeId = 0

    var superType = SuperType.Expense.type

}