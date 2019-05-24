package com.bill.google

import android.content.Context
import com.bill.util.IConstant
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class MobileAdsHelper {

    companion object{

        fun init(context: Context){
            when(IConstant.debugMode){
                true ->{
                    AdRequest.Builder().addTestDevice("D7FC5393EE37AE5DD4BA390BA5E16A97")
                }
                false ->{
                    MobileAds.initialize(context, "ca-app-pub-3628895359109901~3411197712")
                }
            }
        }

    }
}