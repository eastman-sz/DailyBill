package com.bill.billbook

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.bill.base.BaseBillView
import com.bill.bill.BillListActivity
import com.bill.empty.BaseEmptyView
import com.common.base.CommonTitleView
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bill_book_view.view.*

/**
 * Created by E on 2018/3/13.
 */
class BillbookView : BaseBillView{

    constructor(context: Context) : super(context){
        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("Bill Book")
        commonTitleView.setLeftBtnVisibility(View.INVISIBLE)
        commonTitleView.setRightBtnText("create")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onRightBtnClick() {
                context.startActivity(Intent(context , AddBillbookActivity::class.java))
            }
        })
    }

    override fun initViews() {
        View.inflate(context , R.layout.bill_book_view , this)

        val list = ArrayList<Billbook>()
        list.addAll(BillbookDbHelper.getBillbooks())

        val adapter = BillbookAdapter(context , list)
        listView.adapter = adapter

        addEmptyView(listView)

        listView.setOnItemClickListener { parent, view, position, id ->
            val billBook = list[position]

            context.startActivity(Intent(context , BillListActivity::class.java).putExtra("bookId" , billBook.bookId))

        }
    }

    private fun addEmptyView(listview: ListView){
        val emptyView = listview.emptyView
        if (null != emptyView){
            return
        }
        val newEmptyView = BaseEmptyView(context)
        (listview.parent as ViewGroup).addView(newEmptyView)
        listview.emptyView = newEmptyView
        newEmptyView.setEmptyText("点击右上角创建")
    }
}