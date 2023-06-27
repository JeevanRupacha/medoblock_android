package com.example.medoblock

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MedoBlockApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}