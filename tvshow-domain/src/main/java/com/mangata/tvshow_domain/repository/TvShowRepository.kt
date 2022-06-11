package com.mangata.tvshow_domain.repository

import com.mangata.tvshow_domain.model.TvShow

interface TvShowRepository {
    suspend fun getPopularTvShows(pageNumber: Int) : Result<List<TvShow>>
}