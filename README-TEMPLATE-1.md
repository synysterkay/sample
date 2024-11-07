# Apero Tutorial SDK: Template v1

## Init Module
In the class Application
~~~
public void onCreate() {
    super.onCreate();
    ...
    FirebaseApp.initializeApp(this)
    VslFirstOpenSDK.init(this)
}
~~~

## Config SDK
### Step 1. Create Splash screen
The your Splash screen must extend from VslFOSplashActivity
~~~
class SDKSplashActivity : VslFOSplashActivity() {

    /**
     * Specifies the language code for the Tutorial flow.
     * If the user has not selected a language in the settings, then null is passed by default.
     */
    override fun initDefaultLanguage(): String? {
        // TODO: language code you using for Tutorial flow
        return null
    }

    override fun initTemplate1Config(): VslFOTemplate1Config {
        // TODO: config for template. See more: step 2
    }

    override fun nextScreen(context: Context, data: Bundle?) {
        // TODO: handle logic when tutorial completed
        val selectedLanguageCode = data?.getString(VslFirstOpenSDK.ARG_KEY_SELECTED_LANGUAGE_CODE)
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
Provide VslFOTemplate1Config object
~~~
VslFOTemplate1Config(splashConfig, languageConfig, onboardingConfig)
~~~
### 1. splashConfig
~~~
/**
 * banner: list ad unit for banner splash
 * interstitialAd: list ad unit for interstitial splash
 */
VslFOTemplate1Config.SplashConfig(val banner: List<String>, val interstitialAd: List<String>)
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
### 3. onboardingConfig
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
 * nativeAdIds: list ad unit. Notice: The list of ad unit IDs must be sorted in descending order of priority.
 * layoutId: id of native layout 
 * layoutId2 (optional): id of other native layout when you use 2 layouts
 */
NativeAdConfig( val nativeAdIds: List<String>, val layoutId: Int, val layoutId2: Int? = null)
~~~

## Notes
- The list of ad unit IDs must be sorted in descending order of priority