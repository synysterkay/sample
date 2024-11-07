package com.apero.tutorialsdk.sample

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View

class LanguageDialog(context: Context, private val onDismiss: () -> Unit) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_language)
        findViewById<View>(R.id.ll_english).setOnClickListener {
            updateSelectedLanguageCode("en-US")
        }
        findViewById<View>(R.id.ll_france).setOnClickListener {
            updateSelectedLanguageCode("fr")
        }
        setOnDismissListener {
            onDismiss.invoke()
        }
    }

    private fun updateSelectedLanguageCode(code: String) {
        SharedPref.getPrefsInstance().setSelectedLanguageCode(code)
        dismiss()
    }
}