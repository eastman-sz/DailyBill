package com.bill.consumption.type.add

import android.content.Context
import android.view.View
import com.bill.consumption.type.SmallType
import com.bill.consumption.type.SmallTypeDbHelper
import com.bill.consumption.type.SuperType
import com.bill.dialog.DialogHelper
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.common.swipe.SwipeMenuLayout
import com.sz.kk.daily.bill.R

class EditSmallTypeAdapter : IBaseAdapter<SmallType>{

    var superType = SuperType.Expense.type

    constructor(context: Context , list: List<SmallType>) : super(context, list, R.layout.edit_smalltype_adapter)

    override fun getConvertView(convertView: View, list: List<SmallType>, position: Int) {
        val swipeMenuLayout = ViewHolder.getView<SwipeMenuLayout>(convertView , R.id.swipeMenuLayout)
        val textView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.textView)
        val removeTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.removeTextView)
        val reNameTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.reNameTextView)

        val smallType = list[position]

        textView.text = smallType.typeName

        removeTextView.setOnClickListener {
            swipeMenuLayout.smoothClose()
            SmallTypeDbHelper.delete(smallType.typeId)
        }

        reNameTextView.setOnClickListener {
            swipeMenuLayout.smoothClose()

            DialogHelper.showEditSmallTypeNameDialog(context , superType , smallType.typeId)
        }
    }

}