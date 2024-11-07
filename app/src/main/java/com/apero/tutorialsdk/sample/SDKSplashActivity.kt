package com.apero.tutorialsdk.sample

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.apero.firstopen.vsltemplate1.VslFOTemplate1Config
import com.apero.firstopen.vsltemplate1.VslFirstOpenSDK
import com.apero.firstopen.vsltemplate1.model.VslFOLanguageModel
import com.apero.firstopen.vsltemplate1.splash.VslFOSplashActivity
import com.apero.firstopen.vsltemplatecore.config.LanguageConfig
import com.apero.firstopen.vsltemplatecore.config.NativeAdConfig
import com.apero.firstopen.vsltemplatecore.config.OnboardingConfig
import com.apero.firstopen.vsltemplatecore.config.SplashConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

@SuppressLint("CustomSplashScreen")
class SDKSplashActivity : VslFOSplashActivity() {
    private companion object {
        private const val TAG: String = "SDKSplashActivity"
    }

    override fun initDefaultLanguage(): String? {
        // Language code you using for Tutorial flow
        val selectedLanguageCode = SharedPref.getPrefsInstance().getSelectedLanguageCode()
        if (selectedLanguageCode.isEmpty()) {
            return null
        }
        return selectedLanguageCode
    }

    override fun initTemplate1Config(): VslFOTemplate1Config {
        val splashConfig = SplashConfig(
            banner = listOf(
                BuildConfig.banner_splash_priority_1,
                BuildConfig.banner_splash_priority_2
            ),
            interstitialAd = listOf(
                BuildConfig.inter_splash_priority_1,
                BuildConfig.inter_splash_priority_2
            ),
        )
        val languageConfig = LanguageConfig(
            layoutId = R.layout.activity_language,
            itemLayoutId = R.layout.item_language,
            nativeAd1 = NativeAdConfig(
                nativeAdIds = listOf(
                    BuildConfig.native_lfo_priority_1,
                    BuildConfig.native_lfo_priority_2
                ),
                layoutId = R.layout.layout_native_medium
            ),
            nativeAd2 = NativeAdConfig(
                nativeAdIds = listOf(
                    BuildConfig.native_lfo_2_priority_1,
                    BuildConfig.native_lfo_2_priority_2,
                    BuildConfig.native_lfo_2_priority_3,
                    BuildConfig.native_lfo_2_priority_4
                ),
                layoutId = R.layout.layout_native_medium,
                layoutId2 = R.layout.layout_native_medium_other
            ),
            listLanguage = initListLanguage(),
            languageSelected = VslFOLanguageModel(
                R.drawable.ic_en,
                getString(R.string.setting_uk),
                "en"
            )
        )
        val onboardingConfig = OnboardingConfig(
            layoutId = R.layout.activity_onboarding,
            listOnboarding = initListOnboarding()
        )
        return VslFOTemplate1Config(splashConfig, languageConfig, onboardingConfig)
    }

    private fun initListLanguage(): List<VslFOLanguageModel> {
        return listOf(
            VslFOLanguageModel(R.drawable.ic_en, getString(R.string.setting_uk), "en"),
            VslFOLanguageModel(R.drawable.ic_es, getString(R.string.setting_es), "es"),
            VslFOLanguageModel(R.drawable.ic_fr, getString(R.string.setting_fr), "fr"),
            VslFOLanguageModel(R.drawable.ic_hi, getString(R.string.setting_hi), "hi"),
            VslFOLanguageModel(R.drawable.ic_pt, getString(R.string.setting_pt), "pt"),
            VslFOLanguageModel(R.drawable.ic_rs, getString(R.string.setting_rs), "ru")
        )
    }

    private fun initListOnboarding(): List<OnboardingConfig.IOnboardingData> {
        return listOf(
            OnboardingConfig.IOnboardingData.OnboardingContent(
                layoutId = R.layout.fragment_onboarding_1,
                nativeAd = NativeAdConfig(
                    nativeAdIds = listOf(
                        BuildConfig.native_ob1_priority_1,
                        BuildConfig.native_ob1_priority_2
                    ),
                    layoutId = R.layout.layout_native_medium
                )
            ),
            OnboardingConfig.IOnboardingData.OnboardingContent(
                layoutId = R.layout.fragment_onboarding_1,
                nativeAd = NativeAdConfig(
                    nativeAdIds = listOf(
                        BuildConfig.native_ob2_priority_1,
                        BuildConfig.native_ob2_priority_2
                    ),
                    layoutId = R.layout.layout_native_medium
                )
            ),
            OnboardingConfig.IOnboardingData.OnboardingAdFullScreen(
                layoutId = R.layout.fragment_onboarding_ad_full_screen,
                nativeAd = NativeAdConfig(
                    nativeAdIds = listOf(
                        BuildConfig.native_ob_full_scr_priority_1,
                        BuildConfig.native_ob_full_scr_priority_2,
                        BuildConfig.native_ob_full_scr_priority_3,
                        BuildConfig.native_ob_full_scr_priority_4
                    ),
                    layoutId = R.layout.layout_onboarding_native_fullscreen
                )
            ),
            OnboardingConfig.IOnboardingData.OnboardingContent(
                layoutId = R.layout.fragment_onboarding_1,
                nativeAd = NativeAdConfig(
                    nativeAdIds = listOf(
                        BuildConfig.native_ob_4_priority_1,
                        BuildConfig.native_ob_4_priority_2
                    ),
                    layoutId = R.layout.layout_native_medium
                )
            )
        )
    }

    override fun nextScreen(context: Context, data: Bundle?) {
        val intent = Intent(context, MainActivity::class.java)
        val selectedLanguageCode = data?.getString(VslFirstOpenSDK.ARG_KEY_SELECTED_LANGUAGE_CODE)
        Log.d(TAG, "Selected language code: $selectedLanguageCode")
        selectedLanguageCode?.let {
            SharedPref.getPrefsInstance().setSelectedLanguageCode(selectedLanguageCode)
        }
        context.startActivity(intent)
    }

    override fun handleRemoteConfig(remoteConfig: FirebaseRemoteConfig) {
        SharedPref.getPrefsInstance().setYourRemoteConfig(
            remoteConfig.getString(SharedPref.KEY_YOUR_REMOTE)
        )
    }

    override fun afterFetchRemote() {
        super.afterFetchRemote()
        // TODO: handle logic after fetched remote config
        Log.d(TAG, "Firebase remote config fetched.")
    }

    override fun updateUI(savedInstanceState: Bundle?) {

    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }
}