package com.wildcard.eMission

import android.app.Application
import timber.log.Timber

class EmissionApplication : Application() {
    companion object {
        val PREF_NAME: String = "userpreferences"
        val PREF_APPARTMENT: String = "prefappartment"
        val PREF_TRANSPORTATION: String = "preftransportation"
        val PREF_OPTION_TRANSPORTATION_CAR: String = "car"
        val PREF_OPTION_TRANSPORTATION_TRAIN: String = "train"
        val PREF_OPTION_TRANSPORTATION_BUS: String = "bus"
        val PREF_OPTION_TRANSPORTATION_BICYCLE: String = "bicycle"
        val PREF_VEGETARIAN: String = "prefvegetarian"
        val PREF_ONBOARDING: String = "showonboarding"
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}