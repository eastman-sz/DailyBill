package com.bill.billbook

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.bill.base.BaseBillView
import com.bill.bill.BillListActivity
import com.bill.dialog.DialogHelper
import com.bill.dialog.OnCommonItemClickListener
import com.bill.empty.BaseEmptyView
import com.bill.util.BroadcastAction
import com.common.base.CommonTitleView
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bill_book_view.view.*
/**
 * Created by E on 2018/3/13.
 */
class BillbookView : BaseBillView{

    val list = ArrayList<Billbook>()
    var adapter : BillbookAdapter ?= null

    constructor(context: Context) : super(context){
        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("账簿")
        commonTitleView.setLeftBtnVisibility(View.INVISIBLE)
        commonTitleView.setRightBtnText("新建账簿")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onRightBtnClick() {
                context.startActivity(Intent(context , AddBillbookActivity::class.java))
            }
        })
    }

    override fun initViews() {
        View.inflate(context , R.layout.bill_book_view , this)

        adapter = BillbookAdapter(context , list)
        listView.adapter = adapter

        freshData()

        addEmptyView()

        listView.setOnItemClickListener { parent, view, position, id ->
            val billBook = list[position]

            context.startActivity(Intent(context , BillListActivity::class.java).putExtra("bookId" , billBook.bookId))

        }

        listView.setOnItemLongClickListener { parent, view, position, id ->
           DialogHelper.showCommonDialog(context , "温馨提示" , "确定删除此账簿吗?" , "取消" , "确定" , object : OnCommonItemClickListener<Int>{
               override fun onItemClick(it: Int) {
                   when(it){
                        1 ->{
                            val billBook = list[position]
                            BillbookDbHelper.delete(billBook.bookId)

                            list.removeAt(position)
                            adapter?.notifyDataSetChanged()
                        }
                   }
               }
           })
            true
        }
    }

    private fun freshData(){
        list.clear()
        list.addAll(BillbookDbHelper.getBillbooks())
        adapter?.notifyDataSetChanged()
    }

    private fun addEmptyView(){
        val emptyView = listView.emptyView
        if (null != emptyView){
            return
        }
        val newEmptyView = BaseEmptyView(context)
        (listView.parent as ViewGroup).addView(newEmptyView)
        listView.emptyView = newEmptyView
        newEmptyView.setEmptyText("点击右上角创建")
    }

    override fun addBroadCastAction(): java.util.ArrayList<String> {
        val list = ArrayList<String>()
        list.add(BroadcastAction.NEW_ADD_BILL_BOOK)
        return list
    }

    override fun onBroadCastReceive(context: Context?, action: String?, intent: Intent?) {
        when(action){
            BroadcastAction.NEW_ADD_BILL_BOOK -> {
                freshData()
            }
        }
    }
}