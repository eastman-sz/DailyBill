package com.bill.daylist.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.billbook.BillBook
import com.bill.dialog.DialogHelper
import com.common.base.BaseKotlinRelativeLayout
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.billbook_filter_view.view.*
/**
 * 帐本选择.
 * @author E
 */
class BillBookFilterView : BaseKotlinRelativeLayout{

    var bookId = 0L

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.billbook_filter_view , this)
    }

    override fun initListener() {
        billBookFilterLayout.setOnClickListener {
            DialogHelper.showBillBookSelectDialog(context , object : OnCommonItemClickListener<BillBook>(){
                override fun onItemClick(it: BillBook) {
                    bookId = it.bookId
                    billBookNameTextView.text = it.name

                }
            })
        }
    }




}