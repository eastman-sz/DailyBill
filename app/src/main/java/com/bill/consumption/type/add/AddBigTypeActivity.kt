package com.bill.consumption.type.add

import android.os.Bundle
import android.view.View
import com.bill.consumption.type.BigTypeDbHelper
import com.bill.util.BroadcastAction
import com.bill.util.BroadcastUtil
import com.common.base.BaseAppCompactActivity
import com.common.base.ITextChangedListener
import com.common.base.OnCommonTitleClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_add_big_type.*

/**
 * 添加一级分类。
 * @author E
 */
class AddBigTypeActivity : BaseAppCompactActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_big_type)
        initActivity()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("添加一级分类")
        commonTitleView.setRightBtnText("确定")
        commonTitleView.setRightBtnVisibility(View.INVISIBLE)
        commonTitleView.setRightBtnEnabled(false)
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                finish()
            }
            override fun onRightBtnClick() {
                //保存一级分类
                BigTypeDbHelper.save(editText.text.toString())
                finish()

                //发送广播
                BroadcastUtil.sendBroadCast(BroadcastAction.bigTypeFresh)
            }
        }
    }

    override fun initListener() {
        editText.addTextChangedListener(object : ITextChangedListener(){
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                freshBtnEnable(s.toString())
            }
        })
    }

    private fun freshBtnEnable(s : String){
        commonTitleView.setRightBtnVisibility(if (s.isEmpty()) View.INVISIBLE else View.VISIBLE)
        commonTitleView.setRightBtnEnabled(s.isNotEmpty())
    }






}
