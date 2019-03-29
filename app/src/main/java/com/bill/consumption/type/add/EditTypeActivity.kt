package com.bill.consumption.type.add

import android.os.Bundle
import com.bill.base.BaseKotlinActivity
import com.bill.consumption.type.BigType
import com.bill.consumption.type.BigTypeDbHelper
import com.common.base.OnCommonTitleClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_edit_type.*

class EditTypeActivity : BaseKotlinActivity() {

    private val list = ArrayList<BigType>()
    private var adapter : BigTypeAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_type)
        initActivity()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("编辑分类")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                finish()
            }
        }
    }

    override fun initViews() {
        list.addAll(BigTypeDbHelper.getBigTypeS())
        adapter = BigTypeAdapter(context, list)
        listView.adapter = adapter
    }


}
