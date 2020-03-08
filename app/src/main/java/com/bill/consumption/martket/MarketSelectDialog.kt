package com.bill.consumption.martket

import android.content.Context
import android.os.Bundle
import com.common.dialog.BaseUpGlideDialog
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.consumption_point_dialog_view.*
/**
 * Created by E on 2018/3/12.
 */
class MarketSelectDialog : BaseUpGlideDialog {

    var onCommonItemClickListener : OnCommonItemClickListener<Market>?= null

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.consumption_point_dialog_view)

        init()
    }

    override fun initViews() {
        marketWheelView.showAllMarkets()
    }

    override fun initListeners() {
        sureBtnTextView.setOnClickListener {
            dismiss()
            val market = marketWheelView.getMarket()

            onCommonItemClickListener?.onItemClick(market)
        }

        editTextView.setOnClickListener {
            val dialog = AddMarketNameDialog(context)
            dialog.show()
        }

/*        addNewPointTextView.setOnClickListener({
            val dialog = AddMarketNameDialog(context)
            dialog.show()
            dialog.onNewMarketAddedListener = object : AddMarketNameDialog.OnNewMarketAddedListener{
                override fun onAdded(market: String) {

                    list.clear()
                    list.addAll(MarketDbHelper.getMarkets())

                    adapter?.notifyDataSetChanged()
                }
            }
        })*/
    }

}