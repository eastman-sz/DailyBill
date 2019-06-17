package com.bill.bill

import android.content.Context
import android.view.View
import com.bill.consumption.martket.MarketDbHelper
import com.bill.consumption.type.SmallTypeDbHelper
import com.bill.consumption.type.SuperType
import com.bill.util.ColorHelper
import com.bill.util.CommonUtil
import com.common.base.CustomFontDigitTextView
import com.common.base.CustomFontTextView
import com.common.base.ViewHolder
import com.handmark.base.IBaseStickyListAdapter
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHelper
/**
 * Created by E on 2018/3/9.
 */
class BillListAdapter : IBaseStickyListAdapter<BillList> {

    private var marketArray = MarketDbHelper.getMarketArray()
    private val incomeSmallTypeNameArray = SmallTypeDbHelper.getNameArray(SuperType.Income.type)

    private val color1 = ColorHelper.getExpenseTextColor()
    private val color2 = ColorHelper.getIncomeTextColor()
    //0全显示 1只显示支出总数 2只显示收入部数
    var type = 0

    constructor(context: Context , list : List<BillList>) :
            super(context , list , R.layout.bill_list_adapter_view , R.layout.adapter_header_view){
    }

    override fun getConvertView(convertView: View?, list: List<BillList>, position: Int) {
        val amountTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.amountTextView)
        val marketTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.marketTextView)
        val timeTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.timeTextView)
        val remarksTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.remarksTextView)

        val billList = list[position]

        val superType = billList.superType
        val marketId = billList.marketId
        val amount = billList.amount
        val billTime = billList.billtime
        val remarks = billList.remarks
        val dayOfWeek = DateHelper.getDayOfWeekString(billTime)

        if (superType == SuperType.Expense.type){
            marketTextView.text = marketArray.get(marketId)?.marketName
            amountTextView.setTextColor(color1)
        }else{
            val smallTypeId = billList.smallTypeId
            marketTextView.text = incomeSmallTypeNameArray.get(smallTypeId)
            amountTextView.setTextColor(color2)
        }
        amountTextView.text = CommonUtil.trimLastZero(amount.toString())
        timeTextView.text = DateHelper.timestampFormat(billTime , "MM-dd").plus("   ").plus(dayOfWeek)
        remarksTextView.text = "备注: ".plus(if (remarks.isEmpty()){"无"}else{remarks})
        remarksTextView.visibility = if (remarks.isEmpty()){View.GONE}else{View.VISIBLE}
    }

    override fun getHeaderConvertView(convertView: View, list: List<BillList>, position: Int) {
        val headerTextView = ViewHolder.getView(convertView , R.id.headerTextView) as CustomFontTextView
        val monthExpanseAmountTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.monthExpanseAmountTextView)
        val monthIncomeAmountTextView = ViewHolder.getView<CustomFontDigitTextView>(convertView , R.id.monthIncomeAmountTextView)

        val billList = list[position]
        val billTime = billList.billtime
        val monthAmount = billList.monthExpanseAmount
        val monthIncomeAmount = billList.monthIncomeAmount

        headerTextView.text = DateHelper.timestampFormat(billTime , "yyyy年MM月")

        monthExpanseAmountTextView.text = "支出: ".plus(CommonUtil.trimLastZero(monthAmount.toString()))
        monthIncomeAmountTextView.text = "收入: ".plus(CommonUtil.trimLastZero(monthIncomeAmount.toString()))

        when(type){
            0 -> {
                monthExpanseAmountTextView.visibility = View.VISIBLE
                monthIncomeAmountTextView.visibility = View.VISIBLE
            }

            1 ->{
                monthExpanseAmountTextView.visibility = View.VISIBLE
                monthIncomeAmountTextView.visibility = View.GONE
            }

            2 ->{
                monthExpanseAmountTextView.visibility = View.GONE
                monthIncomeAmountTextView.visibility = View.VISIBLE
            }

        }

    }

    override fun notifyDataSetChanged() {
        marketArray = MarketDbHelper.getMarketArray()
        super.notifyDataSetChanged()
    }

}