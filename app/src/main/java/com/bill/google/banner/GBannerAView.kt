package com.bill.google.banner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bill.base.BaseKotlinRelativeLayout
import com.bill.util.ILog
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.sz.kk.daily.bill.R
import kotlinx.android.synthetic.main.g_banner_a_view.view.*

class GBannerAView : BaseKotlinRelativeLayout {

    constructor(context: Context) : super(context){init()}

    constructor(context: Context, attrs : AttributeSet) : super(context , attrs){init()}

    override fun initViews() {
        View.inflate(context , R.layout.g_banner_a_view , this)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener(){
            override fun onAdLoaded() {
                ILog.e("=========onAdLoaded===========")
            }
            override fun onAdClicked() {
                ILog.e("=========onAdClicked===========")
            }
            override fun onAdClosed() {
                ILog.e("=========onAdClosed===========")
            }
            override fun onAdFailedToLoad(p0: Int) {
                ILog.e("=========onAdFailedToLoad===========: $p0")
            }
            override fun onAdImpression() {
                ILog.e("=========onAdImpression===========")
            }
            override fun onAdLeftApplication() {
                ILog.e("=========onAdLeftApplication===========")
            }
            override fun onAdOpened() {
                ILog.e("=========onAdOpened===========")
            }
        }
    }

}