package com.mangata.tvshow_data.remote.dto.tvShowResponse

import kotlinx.serialization.Serializable

@Serializable
internal data class TvShowResponseDto(
    val page: Int,
    val results: List<TvShowDto>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
internal data class TvShowDto(
    val id: Int,
    val name: String,
    val genre_ids: List<Int>,
    val backdrop_path: String?,
    val poster_path: String?,
    val first_air_date: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val vote_average: Double,
    val vote_count: Int
)
