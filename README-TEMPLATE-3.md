# Apero Tutorial SDK: Template v3

**Notices**
- From this version and higher, we support multi-languages with multiple countries. Ex: English (US), English (GB) or Portugal (PT), Portugal (BR)... So you need to pay attention to how to implement localize in your code. You can refer to the sample code below:
~~~
public class YourActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        if (newBase != null) {
            super.attachBaseContext(
                updateResources(newBase, languageCode)
            )
        } else {
            super.attachBaseContext(null)
        }
    }

    private fun updateResources(
        context: Context?, language: String,
    ): Context? {
        val contextFormatted: Context?
        val locale: Locale
        if (language.contains("-")) {
            val splitLanguage = language.split("-")
            locale = Locale(splitLanguage[0], splitLanguage[1])
        } else {
            locale = Locale(language)
        }
        Locale.setDefault(locale)
        val res = context?.resources
        val config = Configuration(res?.configuration)
        config.setLocale(locale)
        contextFormatted = context?.createConfigurationContext(config)
        return contextFormatted
    }
}
~~~
- The app needs to fully support the following languages: France (fr), English (en-US), Hindi (hi), Spanish (es), China (zh), Portugal (pt-PT), Russian (ru), Indonesian (in), English (en-PH), Bengal (bn), Portugal (pt-BR), Afrikaans (af-ZA), Deutsch (de), English (en-CA), English (en-GB), Korean (ko), Dutch (nl) 

## Init Module
In the class Application
~~~
public void onCreate() {
    super.onCreate();
    ...
    FirebaseApp.initializeApp(this)
    VslTemplate3FirstOpenSDK.init(this)
}
~~~

## Config SDK
### Step 1. Create Splash screen
The your Splash screen must extend from VslTemplate3SplashActivity ([sample](./app/src/main/java/com/apero/tutorialsdk/sample/SplashTemplate3Activity.kt))
~~~
class SDKSplashActivity : VslTemplate3SplashActivity() {
    
    /**
     * Specifies the language code for the Tutorial flow.
     * If the user has not selected a language in the settings, then null is passed by default.
     */
    override fun initDefaultLanguage(): String? {
        // TODO: language code you using for Tutorial flow
        return null
    }

    override fun initTemplateConfig(): VslTemplate3Config {
        // TODO: config for template. See more: step 2
    }

    override fun nextScreen(context: Context, data: Bundle?) {
        // TODO: handle logic when tutorial completed
        val selectedLanguageCode = data?.getString(VslTemplate3FirstOpenSDK.ARG_KEY_SELECTED_LANGUAGE_CODE)
        Log.d(TAG, "Selected language code: $selectedLanguageCode")
    }

    override fun handleRemoteConfig(remoteConfig: FirebaseRemoteConfig) {
        // TODO: handle logic with remote config
    }
    
    override fun afterFetchRemote() {
       super.afterFetchRemote()
        // TODO: handle logic after fetched remote config
    }

    override fun updateUI(savedInstanceState: Bundle?) {
        // TODO: this function be called on onCreate()
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }
}
~~~

### Step 2. Config template
Provide VslTemplate3Config object
~~~
VslTemplate3Config(splashConfig, languageConfig, questionConfig, onboardingConfig)
~~~
### 1. splashConfig
~~~
/**
 * banner: list ad unit for banner splash.
 * interstitialAd: list ad unit for interstitial splash
 */
SplashConfig(val banner: List<String>, val interstitialAd: List<String>)
~~~
**Notices:**
- activity_splash: require a FrameLayout with id as bannerAdView for banner ad
- The list of ad unit IDs must be sorted in descending order of priority.

### 2. languageConfig
**Notes:**
- activity_language: you must provide 4 widgets
  - button navigate with buttonLanguageNext id
  - RecyclerView for display list language with recyclerViewLanguageList id
  - FrameLayout for native with nativeAdView id
  - Shimmer layout with shimmer_container_native id. Using [ShimmerFrameLayout](https://github.com/facebookarchive/shimmer-android) for shimmer
- item_language: you must provide 3 widgets
  - view for select language status with checkboxLanguageItem id
  - Text view for display language name with titleLanguageItem id
  - Image view for display language flag with flagLanguageItem id
- Change select/unselect language item icon:
  - Override two drawable files: fo_ic_language_selected.xml & fo_ic_language_unselect.xml
~~~
/**
 * layoutId: id of language layout
 * itemLayoutId: id of item language layout
 * nativeAd1: NativeAdConfig of native ad 1
 * nativeAd2: NativeAdConfig of native ad 2
 * listLanguage: list language for language screen in Tutorial Flow
 * languageSelected: item language be selected by default
 */
LanguageConfig(
    @LayoutRes
    val layoutId: Int,
    @LayoutRes
    val itemLayoutId: Int,
    val nativeAd1: NativeAdConfig,
    val nativeAd2: NativeAdConfig,
    val listLanguage: List<VslFOLanguageModel>,
    val languageSelected: VslFOLanguageModel? = null
)

/**
 * nativeAdIds: list ad unit. Notice: The list of ad unit IDs must be sorted in descending order of priority.
 * layoutId: id of native layout 
 * layoutId2 (optional): id of other native layout when you use 2 layouts
 */
NativeAdConfig( val nativeAdIds: List<String>, val layoutId: Int, val layoutId2: Int? = null)
~~~

### 3. questionConfig
**Notes:**
- activity_question: you must provide 4 widgets
  - button navigate with buttonQuestionNext id. For ver 2.1.0 and higher: You need to provide 2 resource (src or background,...) for enable and disable state of this view (ex: the img_question_action_next.xml file)
  - RecyclerView for display list answer with recyclerViewAnswerList id
  - FrameLayout for native with nativeAdView id
  - Shimmer layout with shimmer_container_native id. Using [ShimmerFrameLayout](https://github.com/facebookarchive/shimmer-android) for shimmer
- item_answer: you need provide the widgets
  - view for select answer status with checkboxAnswerItem id
  - Text view for display answer title with titleAnswerItem id
  - (Optional) Text view for display description of this answer with descriptionAnswerItem id
- Change selected/unselected answer item icon:
  - Override two drawable files: fo_ic_answer_selected.xml & fo_ic_answer_unselected.xml
~~~
/**
 * layoutId: id of question layout
 * itemLayoutId: id of item answer layout
 * answers: list answers for question screen in Tutorial Flow
 * nativeAd1: NativeAdConfig of native ad 1
 * nativeAd2: NativeAdConfig of native ad 2
 */
QuestionConfig(
    @LayoutRes
    val layoutId: Int,
    @LayoutRes
    val itemLayoutId: Int,
    val answers: List<VslTemplate3AnswerModel>,
    val nativeAd1: NativeAdConfig,
    val nativeAd2: NativeAdConfig
)

/**
 * title: string id of the answer title
 * description (optional): string id of the answer description
 * isSelected (optional): status of the answer is selected or not
 */
VslTemplate3AnswerModel(
    @StringRes
    val title: Int,
    @StringRes
    val description: Int? = null,
    var isSelected: Boolean = false
)

/**
 * nativeAdIds: list ad unit. Notice: The list of ad unit IDs must be sorted in descending order of priority.
 * layoutId: id of native layout 
 * layoutId2 (optional): id of other native layout when you use 2 layouts
 */
NativeAdConfig( val nativeAdIds: List<String>, val layoutId: Int, val layoutId2: Int? = null)
~~~

### 4. onboardingConfig
**Notes**: We using viewpager to handle onboarding flow: 1 activity and 4 fragment. Please notice when you design xml and provide layout for config. See more layout sample
- activity_onboarding: you must provide two widgets
  - viewpager with viewPagerOnboarding id
  - Dots indicator: using [DotsIndicator](https://github.com/tommybuonomo/dotsindicator) with
    indicatorPageOnboarding id
- fragment_onboarding:
  - Button next with btnNextOnboarding id
  - FrameLayout for native with nativeAdView id
  - Shimmer layout with shimmer_container_native id. Using [ShimmerFrameLayout](https://github.com/facebookarchive/shimmer-android) for shimmer
- fragment_onboarding_fullscreen:
  - include default view with fullScreenDefaultView id
  - FrameLayout for native with nativeAdView id
  - Shimmer layout with shimmer_container_native id. Using [ShimmerFrameLayout](https://github.com/facebookarchive/shimmer-android) for shimmer
~~~
/**
 * layoutId: id of onboarding activity.
 * listOnboarding: list onboarding fragment
 */
OnboardingConfig(
    @LayoutRes
    val layoutId: Int,
    val listOnboarding: List<IOnboardingData>
)

/**
 * layoutId: id of onboarding content layout
 * nativeAd: config of native ad
 */
OnboardingContent(
    @LayoutRes
    val layoutId: Int,
    override val nativeAd: NativeAdConfig
)

/**
 * layoutId: id of onboarding ad fullscreen layout
 * nativeAd: config of native ad
 */
OnboardingAdFullScreen(
    @LayoutRes
    val layoutId: Int,
    override val nativeAd: NativeAdConfig
)

/**
 * nativeAdIds: list ad unit
 * layoutId: id of native layout. Notice: The list of ad unit IDs must be sorted in descending order of priority.
 * layoutId2 (optional): id of other native layout when you use 2 layouts
 */
NativeAdConfig( val nativeAdIds: List<String>, val layoutId: Int, val layoutId2: Int? = null)
~~~