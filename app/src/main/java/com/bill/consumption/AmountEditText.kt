package com.bill.consumption

import android.content.Context
import android.util.AttributeSet
import com.common.base.CustomFontDigitEditText
import com.common.base.ITextChangedListener
import java.util.regex.Pattern

class AmountEditText : CustomFontDigitEditText{

    var onAmountEditTextListener : OnAmountEditTextListener ?= null

    constructor(context: Context) : super(context){
        addListener()
    }

    constructor(context: Context , attrs : AttributeSet) : super(context , attrs){
        addListener()
    }

    constructor(context: Context , attrs : AttributeSet , defStyle : Int) : super(context , attrs , defStyle){
        addListener()
    }

    private fun addListener(){
        addTextChangedListener(object : ITextChangedListener(){
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                val right = checkAmountRight()
                val right = isOnlyPointNumber(text.toString())
                onAmountEditTextListener?.onEditListener(right , s.toString())

                if (right){
                    return
                }
                val msg = s.toString()
                var length = msg.length
                if (length == 0){
                    return
                }
                val startWithPoint = msg.startsWith("." ,true)
                if (startWithPoint){
                    val text = msg.substring(1)
                    setText(text)
                    setSelection(0)
                    return
                }

                val selectionEnd = selectionEnd
                if (selectionEnd == length){
                    val text = msg.substring(0 , length -1)
                    setText(text)
                    setSelection(length -1)

                }else{
                    val text = msg.substring(0 , selectionEnd -1)
                    val text2 = msg.substring(selectionEnd)

                    setText(text.plus(text2))
                    setSelection(selectionEnd -1)
                }
            }
        })
    }

    fun checkAmountRight() : Boolean{
        val text = text.toString()
        if (text.isNullOrEmpty()){
            return false
        }
        if (text.startsWith(".",true)){
            return false
        }
        if (!text.contains(".")){
            return true
        }
        val right  = text.indexOf(".") == text.lastIndexOf(".")
        return right
    }

    //保留两位小数正则
    fun  isOnlyPointNumber(number : String) : Boolean{
        val pattern = Pattern.compile("^\\d+\\.?\\d{0,2}$")
        val matcher = pattern.matcher(number)
        return matcher.matches()
    }


    }