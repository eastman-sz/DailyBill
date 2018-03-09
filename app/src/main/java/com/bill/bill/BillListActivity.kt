package com.bill.bill

import android.os.Bundle
import com.bill.util.ILog
import com.common.base.BaseAppCompactActivitiy
import com.common.base.CommonTitleView
import com.common.dialog.CommonDialog
import com.common.dialog.OnCommonDialogBtnClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_bill_list.*

class BillListActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_list)

        initActivitys()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("List")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
        })
    }

    override fun initViews() {
        val list = ArrayList<BillList>()
        val adapter = BillListAdapter(context , list)
        sticky_list.refreshableView.adapter = adapter

        val dailyBillList = DaiyBillDbHelper.getAllDailyBills()
        dailyBillList.forEach({
            val billList = BillList.fromBill(it)

            list.add(billList)
        })

        list.sort()

        adapter.notifyDataSetChanged()

        sticky_list.refreshableView.setOnItemClickListener { parent, view, position, id ->
            val billList = list[position -1]

            ILog.e("billList: " + billList.amount)
        }

        sticky_list.refreshableView.setOnItemLongClickListener { parent, view, position, id ->
            val dialog = CommonDialog(context)
            dialog.show()
            dialog.setDialogText("提示" , "确定要删除此条记录吗？" , "确定" , "取消")
            dialog.setOnCommonDialogBtnClickListener(object : OnCommonDialogBtnClickListener{
                override fun onLeftBtnClik() {
                    val newPosition = position -1
                    val billList = list[newPosition]

                    DaiyBillDbHelper.delete(billList.bid)

                    runOnUiThread({
                        list.removeAt(newPosition)
                        adapter.notifyDataSetChanged()
                    })
                }
                override fun onRightBtnClik() {
                }
            })

            true
        }
    }
}
