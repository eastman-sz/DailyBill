package com.bill.daylist.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.consumption.type.BigType
import com.bill.consumption.type.OnConsumptionTypeSelectListener
import com.bill.consumption.type.SmallType
import com.bill.consumption.type.SuperType
import com.bill.dialog.DialogHelper
import com.common.base.BaseKotlinRelativeLayout
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.type_filter_view.view.*
/**
 * 消费分类过滤。
 * @author E
 */
class TypeFilterView : BaseKotlinRelativeLayout{

    var superType = SuperType.Expense.type

    var smallTypeId = 0
    var bigTypeId = 0

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.type_filter_view , this)
    }

    override fun initListener() {
        typeFilterLayout.setOnClickListener {
            DialogHelper.showTypeSelectDialog(context , superType , object : OnConsumptionTypeSelectListener{
                override fun onTypeSelect(smallType: SmallType, bigType: BigType) {
                    smallTypeId = smallType.typeId
                    bigTypeId = bigType.typeId
                    typeNameTextView.text = bigType.typeName.plus(" > ${smallType.typeName}")
                }
            })
        }
    }

    fun reset(){
        this.smallTypeId = 0
        this.bigTypeId = 0

        typeNameTextView.text = "全部"
    }

}