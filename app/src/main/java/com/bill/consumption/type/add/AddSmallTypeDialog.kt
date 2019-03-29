package com.bill.consumption.type.add

import android.content.Context
import android.os.Bundle
import com.bill.consumption.type.SmallTypeDbHelper
import com.bill.util.BroadcastAction
import com.bill.util.BroadcastUtil
import com.common.base.OnCommonTitleClickListener
import com.common.dialog.BaseFullScreenDialog
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.ToastHelper
import kotlinx.android.synthetic.main.add_smalltype_dialog.*
/**
 * 添加二级分类.
 * @author E
 */
class AddSmallTypeDialog : BaseFullScreenDialog {

    var bigTypeId = 0

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_smalltype_dialog)
        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("添加二级分类")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.setRightBtnText("确认")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                dismiss()
            }
            override fun onRightBtnClick() {
                add()
            }
        }
    }

    private fun add(){
        val text = editText.text.toString()
        if (text.isBlank() || text.isEmpty()){
            ToastHelper.makeText(context , "请输入名称")
            return
        }
        SmallTypeDbHelper.save(bigTypeId , text)
        dismiss()

        //发送广播
        BroadcastUtil.sendBroadCast(BroadcastAction.smallTypeFresh)
    }

}