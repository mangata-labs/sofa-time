package com.mangata.tvshow_data.util

object Endpoint {
    private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val TMDB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w780/"

    const val popularTvShows = TMDB_BASE_URL + "tv/popular"
}