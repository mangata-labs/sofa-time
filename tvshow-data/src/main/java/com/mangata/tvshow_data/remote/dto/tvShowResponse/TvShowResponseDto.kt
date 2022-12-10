package com.mangata.tvshow_data.remote.dto.tvShowResponse

import kotlinx.serialization.Serializable

@Serializable
internal data class TvShowResponseDto(
    val page: Int? = null,
    val results: List<TvShowDto> = emptyList(),
    val total_pages: Int? = null,
    val total_results: Int? = null
)

@Serializable
internal data class TvShowDto(
    val id: Int? = null,
    val name: String? = null,
    val genre_ids: List<Int> =  emptyList(),
    val backdrop_path: String? = null,
    val poster_path: String? = null,
    val first_air_date: String? = null,
    val origin_country: List<String> = emptyList(),
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)
