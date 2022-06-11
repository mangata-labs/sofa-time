package com.mangata.tvshow_data.repository

import com.mangata.tvshow_data.remote.TmdbService
import com.mangata.tvshow_data.remote.dto.toTvShow
import com.mangata.tvshow_domain.model.TvShow
import com.mangata.tvshow_domain.repository.TvShowRepository

class TvShowRepositoryImpl(private val tmdbService: TmdbService) : TvShowRepository {
    override suspend fun getPopularTvShows(pageNumber: Int): Result<List<TvShow>> {
        return try {
            val response = tmdbService.getPopularTvShows(pageNumber)
            val result = response.results
            Result.success(result.map { it.toTvShow() })
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}