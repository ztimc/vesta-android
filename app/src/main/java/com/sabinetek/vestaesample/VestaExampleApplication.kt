package com.sabinetek.vestaesample

import android.app.Application
import android.os.StrictMode
import com.sabinetek.vesta.Vesta
import com.sabinetek.vesta.VestaConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class VestaExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())



        Vesta.init(
            this,
            VestaConfig(
                "68F4DF8398BB41F89E12A0889636DBBA ",
                "demo"
            )
        )

    }


}