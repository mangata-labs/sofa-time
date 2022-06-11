package com.mangata.sofatime.di

import coil.ImageLoader
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single {
        ImageLoader.Builder(androidContext())
            .crossfade(true)
            .build()
    }
}