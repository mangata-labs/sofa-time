package com.mangata.tvshow_data.di

import android.util.Log
import androidx.room.Room
import com.mangata.tvshow_data.local.database.SofaTimeDatabase
import com.mangata.tvshow_data.local.database.SofaTimeDatabase.Companion.DATABASE_NAME
import com.mangata.tvshow_data.remote.service.TmdbService
import com.mangata.tvshow_data.remote.service.TmdbServiceImpl
import com.mangata.tvshow_data.repository.TvShowRepositoryImpl
import com.mangata.tvshow_domain.repository.TvShowRepository
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
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
            if (BuildConfig.DEBUG) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("HTTP-Client", message)
                        }
                    }
                    level = LogLevel.HEADERS
                }
            }
        }
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            SofaTimeDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single {
        val database = get<SofaTimeDatabase>()
        database.tvShowDao()
    }

    single<TmdbService> { TmdbServiceImpl(client = get(), appConfiguration =  get()) }

    single<TvShowRepository> { TvShowRepositoryImpl(tmdbService = get(), localStorage = get()) }
}