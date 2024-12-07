package com.example.apartmentmanager.util

import android.content.Context
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import java.util.Locale

//Hàm này dùng để thay đổi ngôn ngữ, tạm thời chưa hoàn thiện
class LocaleManager(private val context: Context) {
    fun setLocale(languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }
}
