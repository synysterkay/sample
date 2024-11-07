package com.apero.tutorialsdk.sample

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(
            getContextWithResources(
                newBase,
                SharedPref.getPrefsInstance().getSelectedLanguageCode()
            )
        )
    }

    private fun getContextWithResources(context: Context?, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context?.resources
        val config = Configuration(res?.configuration)
        config.setLocale(locale)
        return context?.createConfigurationContext(config)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.imgLanguage).setOnClickListener {
            val dialog = LanguageDialog(this) {
                recreate()
            }
            dialog.show()
        }
    }
}