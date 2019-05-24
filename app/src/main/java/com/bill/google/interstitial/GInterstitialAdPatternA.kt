package com.bill.google.interstitial

import android.content.Context
import com.bill.google.OnGAdListener
import com.bill.util.ILog
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class GInterstitialAdPatternA {

    private var mInterstitialAd : InterstitialAd ?= null

    var onGAdListener : OnGAdListener?= null

    constructor()

    fun startLoad(context: Context){
        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd?.adUnitId = "ca-app-pub-3628895359109901/4401829262" //test---ca-app-pub-3940256099942544/1033173712
        mInterstitialAd?.loadAd(AdRequest.Builder().build())
        mInterstitialAd?.adListener = object : AdListener(){
            override fun onAdFailedToLoad(p0: Int) {
                ILog.e("-----插页---onAdFailedToLoad-----------")
            }
            override fun onAdLoaded() {
                ILog.e("-----插页---onAdLoaded-----------")
            }
            override fun onAdOpened() {
                ILog.e("-----插页---onAdOpened-----------")
            }
            override fun onAdClosed() {
                ILog.e("-----插页---onAdClosed-----------")
                mInterstitialAd?.loadAd(AdRequest.Builder().build())

                onGAdListener?.onClosed()
            }
            override fun onAdLeftApplication() {
                ILog.e("-----插页---onAdLeftApplication-----------")
                onGAdListener?.onLeftApplication()
            }
        }
    }

    fun show(){
        when(mInterstitialAd?.isLoaded){
            true ->{
                mInterstitialAd?.show()
            }
            false ->{
                ILog.e("The interstitial wasn't loaded yet.")
            }
        }
    }

}