package com.bill.consumption.type.add

import android.os.Bundle
import com.bill.base.BaseKotlinActivity
import com.bill.consumption.type.*
import com.bill.dialog.DialogHelper
import com.common.base.OnCommonTitleClickListener
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.activity_edit_small_type.*

class EditSmallTypeActivity : BaseKotlinActivity() {

    private val list = ArrayList<SmallType>()
    private var adapter : EditSmallTypeAdapter ?= null
    //监听变化
    private val typeFreshBroadcastReceiveListener = TypeFreshBroadcastReceiveListener()

    private var superType = SuperType.Expense.type
    private var bigTypeId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_small_type)
        initActivity()
    }

    override fun getIntentData() {
        superType = intent.getIntExtra("superType" , SuperType.Expense.type)
        bigTypeId = intent.getIntExtra("bigTypeId" , 0)
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("编辑二级分类")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                finish()
            }
        }
    }

    override fun initViews() {
        adapter = EditSmallTypeAdapter(context, list)
        adapter?.superType = superType
        listView.adapter = adapter

        freshViews()
    }

    override fun initListener() {
        bigTypeLayout.setOnClickListener {
            DialogHelper.showBigTypeSelectDialog(context , superType , object : OnCommonItemClickListener<BigType>(){
                override fun onItemClick(it: BigType) {
                    bigTypeId = it.typeId

                    freshViews()
                }
            })
        }

        addTextView.setOnClickListener {
            //添加二级分类
            DialogHelper.showAddSmallTypeDialog(context , superType , bigTypeId)
        }

        //监听二级分类变化
        typeFreshBroadcastReceiveListener.onTypeFreshBroadcastReceiveListener = object : OnTypeFreshBroadcastReceiveListener(){
            override fun onSmallTypeFresh() {
                freshViews()
            }
        }
    }

    private fun freshViews(){
        val bigType = BigTypeDbHelper.getBigType(superType , bigTypeId)
        bigType?.let {
            bigTypeNameTextView.text = it.typeName
        }

        list.clear()
        list.addAll(SmallTypeDbHelper.getSmallTypeS(superType , bigTypeId))
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        typeFreshBroadcastReceiveListener.unRegister()
        super.onDestroy()
    }
}
