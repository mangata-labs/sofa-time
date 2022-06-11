package com.mangata.tvshow_data.di

import com.mangata.tvshow_data.remote.TmdbService
import com.mangata.tvshow_data.remote.TmdbServiceImpl
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
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            if (BuildConfigHelper.isInDebug()) {
                install(Logging)
            }
        }
    }

    single<TmdbService> { TmdbServiceImpl(get()) }

    single<TvShowRepository> { TvShowRepositoryImpl(get())  }
}