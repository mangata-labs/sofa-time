package com.mangata.core.util

data class AppConfiguration (
    val VERSION_NAME: String,
    val VERSION_CODE: Int,
    val IS_IN_DEBUG: Boolean,
    val TMDB_API_KEY: String,
    val TRAKT_CLIENT_ID: String
)