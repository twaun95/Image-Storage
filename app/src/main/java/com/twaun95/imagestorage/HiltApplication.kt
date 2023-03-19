package com.twaun95.imagestorage

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HiltApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() { Timber.plant(Timber.DebugTree()) }

}