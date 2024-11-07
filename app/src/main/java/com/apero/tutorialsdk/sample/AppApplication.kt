package com.apero.tutorialsdk.sample

import android.app.Application
import com.ads.control.ads.AperoAd
import com.ads.control.config.AdjustConfig
import com.ads.control.config.AperoAdConfig
import com.apero.firstopen.vsltemplate1.VslFirstOpenSDK
import com.apero.firstopen.vsltemplate2.VslTemplate2FirstOpenSDK
import com.apero.firstopen.vsltemplate3.VslTemplate3FirstOpenSDK
import com.google.firebase.FirebaseApp


class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        SharedPref.initPrefs(this)
        initAd()
        FirebaseApp.initializeApp(this)
        initTutorialSDK()
    }

    private fun initAd(): AperoAd {
        val aperoAd: AperoAd = AperoAd.getInstance()
        val environment = AperoAdConfig.ENVIRONMENT_DEVELOP
        val aperoAdConfig = AperoAdConfig(
            this,
            AperoAdConfig.PROVIDER_ADMOB,
            environment
        ).apply {
            apiKey = API_KEY
            adjustConfig = AdjustConfig(ADJUST_ID)
        }
        aperoAd.init(this, aperoAdConfig, false)
        aperoAd.initAdsNetwork()
        return aperoAd
    }

    private fun initTutorialSDK() {
        // VslFirstOpenSDK.init(this) // For template 1
        // VslTemplate2FirstOpenSDK.init(this) // For template 2
        VslTemplate3FirstOpenSDK.init(this) // For template 3
    }

    companion object {
        private lateinit var instance: AppApplication
        fun getInstance(): AppApplication = instance

        private const val ADJUST_ID = "ADJUST_ID"
        private const val API_KEY =
            """nSU7IJ740v46YgI7+Q854tSTyet6l4rDvd+66ex27XHg49C4sALwHTvcAGdmJbqsMClJN9piQ6wN8dbBTy02bE4F1CuZCWCsCvzm5hs77d8iMJ5DdFYZyw7HfbcutmH/9x5Y38vhQAo6knjGfvMxCmJx05Q+NHtPLINtHc4nKdg="""
    }
}