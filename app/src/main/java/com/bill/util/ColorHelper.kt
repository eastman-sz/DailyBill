package com.bill.util

import android.support.v4.content.ContextCompat
import com.bill.application.IApplication
import com.sz.kk.daily.bill.R

class ColorHelper {

    companion object{

        fun getExpenseTextColor() : Int{
            return ContextCompat.getColor(IApplication.context , R.color.c27)
        }

        fun getIncomeTextColor() : Int{
            return ContextCompat.getColor(IApplication.context , R.color.c39)
        }

    }

}