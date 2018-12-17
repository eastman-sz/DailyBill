package com.bill.base

open class OnCommonRequestListener<T> {

    open fun onStart(){}

    open fun onFailed(){}

    open fun onSuccess(it : T){}

}