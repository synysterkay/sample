package com.apero.tutorialsdk.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.ads.control.ads.AperoAd
import com.apero.firstopen.vsltemplate3.VslTemplate3Config
import com.apero.firstopen.vsltemplate3.VslTemplate3FirstOpenSDK
import com.apero.firstopen.vsltemplate3.config.QuestionConfig
import com.apero.firstopen.vsltemplate3.model.VslTemplate3AnswerModel
import com.apero.firstopen.vsltemplate3.model.VslTemplate3LanguageModel
import com.apero.firstopen.vsltemplate3.splash.VslTemplate3SplashActivity
import com.apero.firstopen.vsltemplatecore.config.LanguageConfig
import com.apero.firstopen.vsltemplatecore.config.NativeAdConfig
import com.apero.firstopen.vsltemplatecore.config.OnboardingConfig
import com.apero.firstopen.vsltemplatecore.config.SplashConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

/**
 * Created by KO Huyn on 29/06/2024.
 */
class SplashTemplate3Activity : VslTemplate3SplashActivity() {
    private companion object {
        private const val TAG: String = "SplashTemplate3Activity"
    }

    override fun initDefaultLanguage(): String? {
        // Language code you using for Tutorial flow
        val selectedLanguageCode = SharedPref.getPrefsInstance().getSelectedLanguageCode()
        if (selectedLanguageCode.isEmpty()) {
            return null
        }
        return selectedLanguageCode
    }

    override fun initTemplateConfig(): VslTemplate3Config {
        return VslTemplate3Config(
            splashConfig = getSplashConfig(),
            languageConfig = getLanguageConfig(),
            onboardingConfig = getOnBoardingConfig(),
            questionConfig = getQuestionConfig()
        )
    }

    private fun getSplashConfig(): SplashConfig {
        return SplashConfig(
            banner = listOf(
                BuildConfig.banner_splash_priority_1,
                BuildConfig.banner_splash_priority_2
            ),
            interstitialAd = listOf(
                BuildConfig.inter_splash_priority_1,
                BuildConfig.inter_splash_priority_2,
                BuildConfig.inter_splash_priority_3,
                BuildConfig.inter_splash_priority_4,
                BuildConfig.inter_splash_priority_5,
            ),
        )
    }

    private fun getLanguageConfig(): LanguageConfig {
        return LanguageConfig(
            layoutId = R.layout.activity_language,
            itemLayoutId = R.layout.item_language,
            nativeAd1 = NativeAdConfig(
                nativeAdIds = listOf(
                    BuildConfig.native_lfo_priority_1,
                    BuildConfig.native_lfo_priority_2,
                    BuildConfig.native_lfo_priority_3,
                    BuildConfig.native_lfo_priority_4
                ),
                layoutId = com.ads.control.R.layout.custom_native_admod_medium
            ),
            nativeAd2 = NativeAdConfig(
                nativeAdIds = listOf(
                    BuildConfig.native_lfo_2_priority_1,
                    BuildConfig.native_lfo_2_priority_2,
                    BuildConfig.native_lfo_2_priority_3,
                    BuildConfig.native_lfo_2_priority_4
                ),
                layoutId = com.ads.control.R.layout.custom_native_admod_medium
            ),
            listLanguage = listOf(
                VslTemplate3LanguageModel("fr"),
                VslTemplate3LanguageModel("en-US"),
                VslTemplate3LanguageModel("hi"),
                VslTemplate3LanguageModel("es"),
                VslTemplate3LanguageModel("zh"),
                VslTemplate3LanguageModel("pt-PT"),
                VslTemplate3LanguageModel("pt-BR"),
            ),
            languageSelected = null
        )
    }

    private fun getOnBoardingConfig(): OnboardingConfig {
        return OnboardingConfig(
            layoutId = R.layout.activity_onboarding,
            listOnboarding = listOf(
                OnboardingConfig.IOnboardingData.OnboardingContent(
                    layoutId = R.layout.fragment_onboarding_1,
                    nativeAd = NativeAdConfig(
                        nativeAdIds = listOf(
                            BuildConfig.native_ob1_priority_1,
                            BuildConfig.native_ob1_priority_2,
                            BuildConfig.native_ob1_priority_3,
                            BuildConfig.native_ob1_priority_4
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
        )
    }

    private fun getQuestionConfig(): QuestionConfig {
        return QuestionConfig(
            R.layout.activity_question,
            R.layout.item_answer,
            listOf(
                VslTemplate3AnswerModel(
                    R.string.title_answer_A,
                    R.string.description_answer_A
                ),
                VslTemplate3AnswerModel(
                    R.string.title_answer_B,
                    R.string.description_answer_B
                ),
                VslTemplate3AnswerModel(R.string.title_answer_C)
            ),
            nativeAd1 = NativeAdConfig(
                nativeAdIds = listOf(
                    BuildConfig.native_question_priority_1,
                    BuildConfig.native_question_priority_2
                ),
                layoutId = com.ads.control.R.layout.custom_native_admod_medium
            ),
            nativeAd2 = NativeAdConfig(
                nativeAdIds = listOf(
                    BuildConfig.native_question_2_priority_1,
                    BuildConfig.native_question_2_priority_2
                ),
                layoutId = com.ads.control.R.layout.custom_native_admod_medium
            )
        )
    }

    override fun nextScreen(context: Context, data: Bundle?) {
        val intent = Intent(context, MainActivity::class.java)
        val selectedLanguageCode =
            data?.getString(VslTemplate3FirstOpenSDK.ARG_KEY_SELECTED_LANGUAGE_CODE)
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
        AperoAd.getInstance().isShowMessageTester = true
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }
}