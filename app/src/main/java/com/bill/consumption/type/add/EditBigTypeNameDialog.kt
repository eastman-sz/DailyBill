package com.bill.consumption.type.add

import android.content.Context
import android.os.Bundle
import com.bill.consumption.type.BigTypeDbHelper
import com.bill.consumption.type.SuperType
import com.bill.dialog.BaseFullScreenDialog
import com.common.base.OnCommonTitleClickListener
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.ToastHelper
import kotlinx.android.synthetic.main.edit_smalltype_name_dialog.*
/**
 * 编辑二级分类的名称。
 * @author E
 */
class EditBigTypeNameDialog : BaseFullScreenDialog {

    private var bigTypeId = 0

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_smalltype_name_dialog)
        init()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("修改名称")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.setRightBtnText("确定")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                dismiss()
            }
            override fun onRightBtnClick() {
                update()
            }
        }
    }

    fun setBigTypeId(bigTypeId : Int){
        this.bigTypeId = bigTypeId

        val bigType = BigTypeDbHelper.getBigType(bigTypeId)
        curNameTextView.text = "当前名称: ${bigType?.typeName}"
    }

    private fun update(){
        val text = editText.text.toString()
        if (text.isBlank() || text.isEmpty()){
            ToastHelper.makeText(context , "请输入新的名称")
            return
        }
        dismiss()
        BigTypeDbHelper.updateTypeName(SuperType.Expense.type , bigTypeId , text)
    }

}