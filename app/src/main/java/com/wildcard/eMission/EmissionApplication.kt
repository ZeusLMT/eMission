package com.wildcard.eMission

import android.app.Application
import timber.log.Timber

class EmissionApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}