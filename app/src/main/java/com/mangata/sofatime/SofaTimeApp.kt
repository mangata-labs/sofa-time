package com.mangata.sofatime

import android.app.Application
import com.mangata.sofatime.di.appModule
import com.mangata.tvshow_data.di.remoteModule
import com.mangata.tvshow_presentation.di.tvShowModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SofaTimeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@SofaTimeApp)
            modules(appModule)
            modules(remoteModule)
            modules(tvShowModule)
        }
    }
}