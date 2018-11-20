package com.bill.daylist

import android.os.Bundle
import android.view.View
import com.bill.base.BaseKotlinActivity
import com.common.base.CommonTitleView
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

        initActivitys()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("筛选")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.setRightBtnVisibility(View.INVISIBLE)
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                finish()
            }
        })
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

                    ListenerConfig.onDailyBillFilterParamSetListener?.onResult(dailyBillFilter)


                }
            }
        }
    }
}
