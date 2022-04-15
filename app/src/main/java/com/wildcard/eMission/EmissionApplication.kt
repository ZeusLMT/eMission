package com.wildcard.eMission

import android.app.Application
import androidx.databinding.ktx.BuildConfig
import timber.log.Timber

class EmissionApplication : Application() {
    companion object {
        const val PREF_NAME: String = "userpreferences"
        const val PREF_APPARTMENT: String = "prefappartment"
        const val PREF_TRANSPORTATION: String = "preftransportation"
        const val PREF_OPTION_TRANSPORTATION_CAR: String = "car"
        const val PREF_OPTION_TRANSPORTATION_TRAIN: String = "train"
        const val PREF_OPTION_TRANSPORTATION_BUS: String = "bus"
        const val PREF_OPTION_TRANSPORTATION_BICYCLE: String = "bicycle"
        const val PREF_VEGETARIAN: String = "prefvegetarian"
        const val PREF_ONBOARDING: String = "showonboarding"
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}