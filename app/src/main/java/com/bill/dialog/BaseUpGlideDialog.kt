package com.bill.dialog

import android.content.Context
import android.view.Gravity
import com.common.dialog.BaseDialog
import com.sz.kk.daily.bill.R
import com.utils.lib.ss.info.DeviceInfo
/**
 * 基础上滑弹出窗。
 * @author E
 */
open class BaseUpGlideDialog : BaseDialog{

    constructor(context: Context) : super(context , R.style.lable_del_dialog)

    override fun show() {
        super.show()

        val attr = window.attributes
        attr.width = DeviceInfo.getScreenWith(context)
        window.attributes = attr

        window.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.share_style)
    }

}