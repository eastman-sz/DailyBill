package com.bill.consumption.type.add

import android.os.Bundle
import com.bill.base.BaseKotlinActivity
import com.bill.consumption.type.BigType
import com.bill.consumption.type.BigTypeDbHelper
import com.bill.consumption.type.SuperType
import com.common.base.OnCommonTitleClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_edit_type.*
import org.jetbrains.anko.startActivity

class EditTypeActivity : BaseKotlinActivity() {

    private val list = ArrayList<BigType>()
    private var adapter : BigTypeAdapter ?= null

    private var superType = SuperType.Expense.type

    private val typeFreshBroadcastReceiveListener = TypeFreshBroadcastReceiveListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_type)
        initActivity()
    }

    override fun getIntentData() {
        superType = intent.getIntExtra("superType" , SuperType.Expense.type)
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
        adapter = BigTypeAdapter(context, list)
        adapter?.superType = superType
        listView.adapter = adapter

        freshViews()
    }

    private fun freshViews(){
        list.clear()
        list.addAll(BigTypeDbHelper.getBigTypeS(superType))
        adapter?.notifyDataSetChanged()
    }

    override fun initListener() {
        typeFreshBroadcastReceiveListener.onTypeFreshBroadcastReceiveListener = object : OnTypeFreshBroadcastReceiveListener(){
            override fun onBigTypeFresh() {
                freshViews()
            }
        }

        //添加一级分类
        addBigTypeTextView.setOnClickListener {
            startActivity<AddBigTypeActivity>()
        }

    }

    override fun onDestroy() {
        typeFreshBroadcastReceiveListener.unRegister()
        super.onDestroy()
    }


}
