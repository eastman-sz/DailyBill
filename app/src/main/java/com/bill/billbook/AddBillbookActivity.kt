package com.bill.billbook

import android.os.Bundle
import android.view.View
import com.bill.base.BaseKotlinActivity
import com.bill.util.BroadcastAction
import com.bill.util.BroadcastUtil
import com.common.base.ITextChangedListener
import com.common.base.OnCommonTitleClickListener
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.common.ToastHelper
import kotlinx.android.synthetic.main.activity_add_billbook.*

class AddBillbookActivity : BaseKotlinActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_billbook)
        initActivity()
    }

    override fun initTitle() {
        commonTitleView.setCenterTitle("新建账簿")
        commonTitleView.setLeftBtnText("返回")
        commonTitleView.onCommonTitleItemClickListener = object : OnCommonTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
        }
    }

    override fun initListener() {
        nameTextView.addTextChangedListener(object : ITextChangedListener(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val emptyText = s.toString().isNullOrEmpty()
                when(emptyText){
                    true ->{
                        createBillBookTextView.setBackgroundResource(R.drawable.gray_disabled_retangle_selector)
                        createBillBookTextView.isEnabled = false
                    }
                    false -> {
                        createBillBookTextView.setBackgroundResource(R.drawable.c1_c2_retangle_selector)
                        createBillBookTextView.isEnabled = true
                    }
                }
            }
        })
    }

    fun onBtnClick(v : View){
        when(v){
            createBillBookTextView ->{
                val name = nameTextView.text.toString()
                BillBookDbHelper.save(name)

                ToastHelper.makeText(context , "创建成功")

                onBackPressed()

                BroadcastUtil.sendBroadCast(BroadcastAction.NEW_ADD_BILL_BOOK)
            }
        }
    }


}
