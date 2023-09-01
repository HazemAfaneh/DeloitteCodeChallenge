package com.kaizenplus.deloittecodechallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.objectbox.BoxStore
import io.objectbox.android.Admin
import io.objectbox.android.BuildConfig
import javax.inject.Inject

@HiltAndroidApp
class AppController:Application() {
    @Inject
    lateinit var boxStore: BoxStore
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Admin(boxStore).start(this)
        }
    }
}