package com.bill.daylist

import android.os.Bundle
import android.view.View
import com.bill.base.BaseKotlinActivity
import com.common.base.OnCommonTitleClickListener
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
        sureBtnTextView.setOnClickListener(listener)
    }

    private val listener = View.OnClickListener {
        when(it){
            sureBtnTextView ->{
                finish()

                startTimestamp = commonDateFilterView.startTimestamp
                endTimestamp = commonDateFilterView.endTimestamp

                runOnUiThread {
                    val dailyBillFilter = DailyBillFilter()
                    dailyBillFilter.startTimestamp = startTimestamp
                    dailyBillFilter.endTimestamp = endTimestamp

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
