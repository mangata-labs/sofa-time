package com.mangata.tvshow_data.di

import com.mangata.tvshow_data.remote.service.TmdbService
import com.mangata.tvshow_data.remote.service.TmdbServiceImpl
import com.mangata.tvshow_data.repository.TvShowRepositoryImpl
import com.mangata.tvshow_data.util.BuildConfigHelper
import com.mangata.tvshow_domain.repository.TvShowRepository
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val remoteModule = module {

    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                })
            }
            if (BuildConfigHelper.isInDebug()) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }
        }
    }

    single<TmdbService> { TmdbServiceImpl(get()) }

    single<TvShowRepository> { TvShowRepositoryImpl(get())  }
}