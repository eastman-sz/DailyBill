package com.bill.wheelview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.billbook.BillBook
import com.bill.billbook.BillBookDbHelper
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.account_book_wheelview.view.*
/**
 * 帐本选择器。
 * @author E
 */
class AccountBookWheelView : BaseKotlinRelativeLayout {

    private val list = ArrayList<BillBook>()

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.account_book_wheelview , this)

        showBillBooks()
    }

    fun showBillBooks(){
        list.clear()
        list.addAll(BillBookDbHelper.getBillBooks())

        val adapter = AccountBookWheelViewAdapter(context, list)
        wheelView.viewAdapter = adapter
        wheelView.visibleItems = 5
        wheelView.setCurrentItem(1 , false)
    }

    fun getBillBook() : BillBook {
        val curPosition = wheelView.currentItem
        val billBook = list[curPosition]
        return billBook
    }


}