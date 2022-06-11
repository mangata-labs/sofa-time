package com.mangata.tvshow_data.remote.dto

import com.mangata.tvshow_data.util.Endpoint
import com.mangata.tvshow_domain.model.TvShow
import kotlinx.serialization.Serializable

@Serializable
data class TvShowResponseDto(
    val page: Int,
    val results: List<TvShowDto>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class TvShowDto(
    val backdrop_path: String?,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val vote_average: Double,
    val vote_count: Int
)

fun TvShowDto.toTvShow() : TvShow {
    return TvShow(
        id = id,
        name = name,
        description = overview,
        posterPath = if (poster_path.isNullOrEmpty()) null else "${Endpoint.TMDB_POSTER_BASE_URL}$poster_path",
        backdropPath = if (backdrop_path.isNullOrEmpty()) null else "${Endpoint.TMDB_POSTER_BASE_URL}$backdrop_path"
    )
}
