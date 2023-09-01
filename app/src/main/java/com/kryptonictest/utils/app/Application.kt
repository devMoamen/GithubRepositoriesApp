package com.kryptonictest.utils.app
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {
    private val TAG = "App"
    private lateinit var instance: Context
    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
    }
}