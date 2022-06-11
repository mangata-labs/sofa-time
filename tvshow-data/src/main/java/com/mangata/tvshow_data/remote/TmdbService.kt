package com.mangata.tvshow_data.remote

import com.mangata.tvshow_data.remote.dto.TvShowResponseDto

interface TmdbService {
    suspend fun getPopularTvShows(pageNumber: Int) : TvShowResponseDto
}