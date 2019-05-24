package com.bill.billbook

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import com.bill.base.BaseBillView
import com.bill.bill.BillListActivity
import com.bill.dialog.DialogHelper
import com.bill.empty.BaseEmptyView
import com.bill.util.BroadcastAction
import com.common.base.OnCommonTitleClickListener
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.bill_book_view.view.*
import org.jetbrains.anko.startActivity
/**
 * Created by E on 2018/3/13.
 */
class BillBookView : BaseBillView{

    val list = ArrayList<BillBook>()
    var adapter : BillbookAdapter ?= null

    constructor(context: Context) : super(context){
        init()
    }

    override fun initTitle() {
        titleRightBtn.setOnClickListener {
            context.startActivity<AddBillBookActivity>()
        }
    }

    override fun initViews() {
        View.inflate(context , R.layout.bill_book_view , this)

/*        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener(){
            override fun onAdLoaded() {
                ILog.e("=========onAdLoaded===========")
            }
            override fun onAdClicked() {
                ILog.e("=========onAdClicked===========")
            }
            override fun onAdClosed() {
                ILog.e("=========onAdClosed===========")
            }
            override fun onAdFailedToLoad(p0: Int) {
                ILog.e("=========onAdFailedToLoad===========: $p0")
            }
            override fun onAdImpression() {
                ILog.e("=========onAdImpression===========")
            }
            override fun onAdLeftApplication() {
                ILog.e("=========onAdLeftApplication===========")
            }
            override fun onAdOpened() {
                ILog.e("=========onAdOpened===========")
            }
        }*/

        adapter = BillbookAdapter(context , list)
        listView.adapter = adapter

        freshData()

        addEmptyView()

        listView.setOnItemClickListener { _, _, position, _ ->
            val billBook = list[position]

            context.startActivity<BillListActivity>("bookId" to billBook.bookId)

        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
           DialogHelper.showCommonDialog(context , "确定删除此账簿吗" , "取消" , "确定"   , object : OnCommonItemClickListener<Int>() {
               override fun onItemClick(it: Int) {
                   when(it){
                        1 ->{
                            val billBook = list[position]
                            BillBookDbHelper.delete(billBook.bookId)

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
        list.addAll(BillBookDbHelper.getBillBooks())
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