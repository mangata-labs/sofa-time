package com.mangata.sofatime.di

import coil.ImageLoader
import com.mangata.core.util.AppConfiguration
import com.mangata.sofatime.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single {
        ImageLoader.Builder(androidContext())
            .crossfade(true)
            .build()
    }

    single {
        AppConfiguration(
            VERSION_NAME = BuildConfig.VERSION_NAME,
            VERSION_CODE = BuildConfig.VERSION_CODE,
            IS_IN_DEBUG = BuildConfig.DEBUG,
            TMDB_API_KEY = BuildConfig.TMDB_API_KEY,
            TRAKT_CLIENT_ID = BuildConfig.TRAKT_CLIENT_ID
        )
    }
}