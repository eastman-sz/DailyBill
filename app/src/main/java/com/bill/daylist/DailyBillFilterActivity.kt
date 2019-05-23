package com.bill.daylist

import android.os.Bundle
import android.view.View
import com.bill.base.BaseKotlinActivity
import com.bill.consumption.type.SuperType
import com.common.base.OnCommonTitleClickListener
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_daily_bill_filter.*
/**
 * 选择过滤条件页面。
 * @author E
 */
class DailyBillFilterActivity : BaseKotlinActivity() {

    var startTimestamp = 0L
    var endTimestamp = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_bill_filter)
        initActivity()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("筛选")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.setRightBtnVisibility(View.INVISIBLE)
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                finish()
            }
        }
    }

    override fun initViews() {

    }

    override fun initListener() {
        resetBtn.setOnClickListener(listener)
        sureBtnTextView.setOnClickListener(listener)

        expanseAndIncomeFilterView.onCommonItemClickListener = object : OnCommonItemClickListener<Int>(){
            override fun onItemClick(it: Int) {
                runOnUiThread {
                    //0全部 1支出  2收入
                    when(it){
                        SuperType.ALL.type ->{
                            typeFilterLayout.visibility = View.GONE
                            extraExpanseLayout.visibility = View.GONE
                        }

                        SuperType.Expense.type ->{
                            typeFilterView.superType = it

                            typeFilterLayout.visibility = View.VISIBLE
                            extraExpanseLayout.visibility = View.VISIBLE
                        }

                        SuperType.Income.type ->{
                            typeFilterView.superType = it

                            typeFilterLayout.visibility = View.VISIBLE
                            extraExpanseLayout.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private val listener = View.OnClickListener {
        when(it){
            resetBtn ->{
                commonDateFilterView.reset()
                expanseAndIncomeFilterView.reset()
                billBookFilterView.reset()
                typeFilterView.reset()
                natureFilterView.reset()
                paymentFilterView.reset()
                marketFilterView.reset()
            }

            sureBtnTextView ->{
                finish()

                startTimestamp = commonDateFilterView.startTimestamp
                endTimestamp = commonDateFilterView.endTimestamp

                runOnUiThread {
                    val dailyBillFilter = DailyBillFilter()
                    dailyBillFilter.startTimestamp = startTimestamp
                    dailyBillFilter.endTimestamp = endTimestamp

                    dailyBillFilter.superType = expanseAndIncomeFilterView.superType

                    dailyBillFilter.bookId = billBookFilterView.bookId

                    dailyBillFilter.bigTypeId = typeFilterView.bigTypeId
                    dailyBillFilter.smallTypeId = typeFilterView.smallTypeId

                    dailyBillFilter.natureId = natureFilterView.natureId
                    dailyBillFilter.paymentId = paymentFilterView.paymentId
                    dailyBillFilter.marketId = marketFilterView.marketId

                    ListenerConfig.onDailyBillFilterParamSetListener?.onResult(dailyBillFilter)
                }
            }
        }
    }
}
