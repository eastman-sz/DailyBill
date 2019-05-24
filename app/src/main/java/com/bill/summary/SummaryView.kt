package com.bill.summary

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bill.base.BaseBillView
import com.bill.summary.expanse.ExpanseSummaryView
import com.bill.summary.income.IncomeSummaryView
import com.common.base.BasePagerAdapter
import com.common.base.BaseRelativeLayout
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.summary_view.view.*
/**
 * Created by E on 2018/3/12.
 */
class SummaryView : BaseBillView{

    constructor(context: Context) : super(context){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.summary_view , this)

        val list = ArrayList<BaseRelativeLayout>()
        list.add(ExpanseSummaryView(context!!))
        list.add(IncomeSummaryView(context!!))

        val adapter = BasePagerAdapter(context , list)
        viewPager.adapter = adapter
    }

    override fun initListener() {
        summaryHeaderView.onCommonItemClickListener = object : OnCommonItemClickListener<Int>(){
            override fun onItemClick(it: Int) {
                mHandler.post {
                    viewPager.setCurrentItem(it , false)
                }
            }
        }
    }

    private val mHandler = Handler(Looper.getMainLooper())


}