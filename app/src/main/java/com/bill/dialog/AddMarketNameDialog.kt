package com.bill.dialog

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import com.bill.point.ConsumptionPointHelper
import com.common.base.CommonTitleView
import com.common.base.ITextChangedListener
import com.common.dialog.BaseDialog
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.ToastHelper
import com.utils.lib.ss.info.DeviceInfo
import kotlinx.android.synthetic.main.add_marketname_dialog_view.*
/**
 * Created by E on 2018/3/12.
 */
class AddMarketNameDialog : BaseDialog{

    constructor(context: Context) : super(context , R.style.lable_del_dialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_marketname_dialog_view)

        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("添加新名称")
        commonTitleView.setLeftBtnText("取消")
        commonTitleView.setRightBtnText("添加")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                dismiss()
            }
            override fun onRightBtnClick() {
                val marketName = editText.text.toString()
                if (marketName.isNullOrEmpty()){
                    return
                }
                val nameList = ConsumptionPointHelper.getAllConsuptionPointNames()
                if (nameList.contains(marketName)){
                    ToastHelper.makeText(context , "名称已存在")
                    return
                }

                ConsumptionPointHelper.add(marketName)

                onNewMarketAddedListener?.onAdded(marketName)

                dismiss()
            }
        })
    }

    override fun initListeners() {
        editText.addTextChangedListener(object : ITextChangedListener(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTextchg(s.toString())
            }
        })
    }

    fun onTextchg(text : String){
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

    override fun show() {
        super.show()

        val attr = window.attributes
        attr.width = DeviceInfo.getScreenWith(context)
        attr.height = DeviceInfo.getScreenHeight(context)
        window.attributes = attr

        window.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.share_style)
    }

    interface OnNewMarketAddedListener {
        fun onAdded(market : String)
    }

    var onNewMarketAddedListener : OnNewMarketAddedListener ?= null

}