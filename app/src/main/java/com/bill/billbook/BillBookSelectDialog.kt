package com.bill.billbook

import android.content.Context
import android.os.Bundle
import com.common.dialog.BaseUpGlideDialog
import com.common.dialog.OnCommonItemClickListener
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.billbook_select_dialog.*
/**
 * 帐本选择.
 * @author E
 */
class BillBookSelectDialog : BaseUpGlideDialog {

    var onCommonItemClickListener : OnCommonItemClickListener<BillBook>?= null

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.billbook_select_dialog)

        init()
    }

    override fun initListeners() {
        sureBtnTextView.setOnClickListener {
            dismiss()
            val billBook = accountBookWheelView.getBillBook()

            onCommonItemClickListener?.onItemClick(billBook)
        }
    }

}