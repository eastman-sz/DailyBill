package com.bill.consumption.martket

import android.content.Context
import android.os.Bundle
import com.common.dialog.BaseUpGlideDialog
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.consumption_point_dialog_view.*
/**
 * Created by E on 2018/3/12.
 */
class MarketSelectDialog : BaseUpGlideDialog {

    val list = ArrayList<Market>()
    var adapter : MarketSelectAdapter?= null

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.consumption_point_dialog_view)

        init()
    }

    override fun initViews() {
        list.addAll(MarketDbHelper.getMarkets())

        adapter = MarketSelectAdapter(context , list)
        itemExpandListview.adapter = adapter

        itemExpandListview.setOnItemClickListener { _, _, position, id ->

            onConsuptionPointSelectListener?.selected(list[position])

            dismiss()
        }

    }

    override fun initListeners() {
        addNewPointTextView.setOnClickListener({
            val dialog = AddMarketNameDialog(context)
            dialog.show()
            dialog.onNewMarketAddedListener = object : AddMarketNameDialog.OnNewMarketAddedListener{
                override fun onAdded(market: String) {

                    list.clear()
                    list.addAll(MarketDbHelper.getMarkets())

                    adapter?.notifyDataSetChanged()
                }
            }
        })
    }

    interface OnConsuptionPointSelectListener{
        fun selected(market: Market)
    }

    var onConsuptionPointSelectListener : OnConsuptionPointSelectListener ?= null

}