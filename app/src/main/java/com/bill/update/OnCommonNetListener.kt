package com.bill.update

open class OnCommonNetListener<T> {

    open fun onStart(){}

    open fun onFailed(){}

    open fun onResult(resultList : List<T>){}

    open fun onResult(it : T){}

    open fun onFailed(resultCode : Int){}

}