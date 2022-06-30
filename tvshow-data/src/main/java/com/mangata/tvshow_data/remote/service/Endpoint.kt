package com.mangata.tvshow_data.remote.service

internal object Endpoint {
    private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"

    fun popularTvShows() = TMDB_BASE_URL + "tv/popular"
    fun tvShowsDetails(id: Int) = TMDB_BASE_URL + "tv/$id"
    fun tvShowTrailer(id: Int) = TMDB_BASE_URL + "tv/$id/videos"
    fun tvShowImages(id: Int) = TMDB_BASE_URL + "tv/$id/images"
}