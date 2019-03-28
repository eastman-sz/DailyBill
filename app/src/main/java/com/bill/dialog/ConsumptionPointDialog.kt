package com.bill.dialog

import android.content.Context
import android.os.Bundle
import com.bill.point.ConsumptionPoint
import com.bill.point.ConsumptionPointHelper
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.consumption_point_dialog_view.*
/**
 * Created by E on 2018/3/12.
 */
class ConsumptionPointDialog : BaseUpGlideDialog {

    val list = ArrayList<ConsumptionPoint>()
    var adapter : ConsumptionPointAdapter ?= null

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.consumption_point_dialog_view)

        init()
    }

    override fun initViews() {
        list.addAll(ConsumptionPointHelper.getAllConsuptionPoints())

        adapter = ConsumptionPointAdapter(context , list)
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
                    list.addAll(ConsumptionPointHelper.getAllConsuptionPoints())

                    adapter?.notifyDataSetChanged()
                }
            }
        })
    }

    interface OnConsuptionPointSelectListener{
        fun selected(consumptionPoint : ConsumptionPoint)
    }

    var onConsuptionPointSelectListener : OnConsuptionPointSelectListener ?= null

}