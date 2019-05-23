package com.bill.consumption.type.add

import android.content.Context
import android.os.Bundle
import com.bill.consumption.type.SmallTypeDbHelper
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
class EditSmallTypeNameDialog : BaseFullScreenDialog {

    private var superType = SuperType.Expense.type
    private var smallTypeId = 0


    constructor(context: Context , superType : Int) : super(context){
        this.superType = superType
    }

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

    fun setSmallTypeId(smallTypeId : Int){
        this.smallTypeId = smallTypeId

        val smallType = SmallTypeDbHelper.getSmallType(superType , smallTypeId)
        curNameTextView.text = "当前名称: ${smallType?.typeName}"
    }


    private fun update(){
        val text = editText.text.toString()
        if (text.isBlank() || text.isEmpty()){
            ToastHelper.makeText(context , "请输入新的名称")
            return
        }
        dismiss()
        SmallTypeDbHelper.updateTypeName(superType, smallTypeId , text)
    }

}