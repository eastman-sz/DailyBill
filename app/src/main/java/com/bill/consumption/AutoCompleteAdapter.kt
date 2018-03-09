package com.bill.consumption

import android.content.Context
import android.widget.ArrayAdapter
import com.sz.kk.daily.bill.R
/**
 * Created by E on 2018/3/9.
 */
class AutoCompleteAdapter : ArrayAdapter<String> {

    constructor(context: Context , list: List<String>) : super(context  , R.layout.auto_complete_adapter_view , R.id.textView , list)



}