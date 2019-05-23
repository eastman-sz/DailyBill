package com.bill.consumption.type.add

import android.content.Context
import android.view.View
import com.bill.consumption.type.BigType
import com.bill.consumption.type.BigTypeDbHelper
import com.bill.consumption.type.SuperType
import com.bill.dialog.DialogHelper
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.common.swipe.SwipeMenuLayout
import com.sz.kk.daily.bill.R
import org.jetbrains.anko.startActivity

class BigTypeAdapter : IBaseAdapter<BigType>{

    var superType = SuperType.Expense.type

    constructor(context: Context , list: List<BigType>) : super(context, list, R.layout.bigtype_adapter)

    override fun getConvertView(convertView: View, list: List<BigType>, position: Int) {
        val swipeMenuLayout = ViewHolder.getView<SwipeMenuLayout>(convertView , R.id.swipeMenuLayout)
        val textView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.textView)
        val removeTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.removeTextView)
        val reNameTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.reNameTextView)

        val bigType = list[position]

        textView.text = bigType.typeName

        textView.setOnClickListener {
            context.startActivity<EditSmallTypeActivity>("superType" to superType , "bigTypeId" to bigType.typeId)
        }

        removeTextView.setOnClickListener {
            swipeMenuLayout.smoothClose()
            //删除
            BigTypeDbHelper.delete(superType , bigType.typeId)
        }

        reNameTextView.setOnClickListener {
            swipeMenuLayout.smoothClose()
            //改名
            DialogHelper.showEditEditBigTypeNameDialog(context , superType , bigType.typeId)
        }
    }

}