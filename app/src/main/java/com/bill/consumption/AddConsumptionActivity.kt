package com.bill.consumption

import android.os.Bundle
import android.view.View
import com.bill.dialog.DateTimeSelectDialog
import com.common.base.BaseAppCompactActivitiy
import com.common.base.CommonTitleView
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.DateHepler
import kotlinx.android.synthetic.main.activity_add_consumption.*

class AddConsumptionActivity : BaseAppCompactActivitiy() {

    var cTimestamp : Long = 0L;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_consumption)

        initActivitys()
    }

    init {
        cTimestamp = System.currentTimeMillis()
    }

    override fun initTitle() {
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.setCenterTitleText("")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
        })
    }

    fun onBtnClick(v : View){
        when(v){
            dateTimeLayout -> {
                val dialog = DateTimeSelectDialog(context)
                dialog.show()
                dialog.setTimestamp(cTimestamp)
                dialog.onDateTimeSelectedListener = object : DateTimeSelectDialog.OnDateTimeSelectedListener{
                    override fun onSelected(timestamp: Long) {
                        runOnUiThread {
                            dateTimeTextView.text = DateHepler.timestampFormat(timestamp , "yyyy-MM-dd HH:mm:ss")
                        }
                    }
                }
            }
        }
    }


}
