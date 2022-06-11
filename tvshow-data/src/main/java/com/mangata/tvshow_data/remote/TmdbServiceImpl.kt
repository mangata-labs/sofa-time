package com.mangata.tvshow_data.remote

import com.mangata.tvshow_data.remote.dto.TvShowResponseDto
import com.mangata.tvshow_data.util.BuildConfigHelper
import com.mangata.tvshow_data.util.Endpoint
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbServiceImpl(private val client: HttpClient) : TmdbService {
    override suspend fun getPopularTvShows(pageNumber: Int): TvShowResponseDto {
        return client.get(Endpoint.popularTvShows) {
            parameter("page", pageNumber)
            parameter("api_key", BuildConfigHelper.getTmdbApiKey())
        }.body()
    }
}