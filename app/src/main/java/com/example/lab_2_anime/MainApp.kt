package com.example.lab_2_anime

import android.app.Application
import android.content.Context

class MainApp : Application() {
    lateinit var appComponent: AppComponent
        private set
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> applicationContext.appComponent
    }