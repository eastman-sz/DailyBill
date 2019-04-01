package com.bill.consumption.martket

import android.content.Context
import android.os.Bundle
import com.bill.consumption.martket.MarketDbHelper
import com.bill.dialog.BaseFullScreenDialog
import com.common.base.ITextChangedListener
import com.common.base.OnCommonTitleClickListener
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.ToastHelper
import kotlinx.android.synthetic.main.add_marketname_dialog_view.*
/**
 * Created by E on 2018/3/12.
 */
class AddMarketNameDialog : BaseFullScreenDialog {

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_marketname_dialog_view)

        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("添加新名称")
        commonTitleView.setLeftBtnText("取消")
        commonTitleView.setRightBtnText("添加")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                dismiss()
            }

            override fun onRightBtnClick() {
                val marketName = editText.text.toString()
                if (marketName.isNullOrEmpty()){
                    return
                }
                val nameList = MarketDbHelper.getAllMarketNames()
                if (nameList.contains(marketName)){
                    ToastHelper.makeText(context , "名称已存在")
                    return
                }

                MarketDbHelper.add(marketName)

                onNewMarketAddedListener?.onAdded(marketName)

                dismiss()
            }
        }
    }

    override fun initListeners() {
        editText.addTextChangedListener(object : ITextChangedListener(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChg(s.toString())
            }
        })
    }

    private fun onTextChg(text : String){
        val empty = text.isNullOrEmpty()
        when(empty){
            false ->{
                commonTitleView.setRightBtnText("添加")
            }
            true ->{
                commonTitleView.setRightBtnText("")
            }
        }
    }

    interface OnNewMarketAddedListener {
        fun onAdded(market : String)
    }

    var onNewMarketAddedListener : OnNewMarketAddedListener ?= null

}