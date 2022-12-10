package com.mangata.tvshow_data.remote.dto.tvShowDetailResponse

import kotlinx.serialization.Serializable

@Serializable
internal data class LastEpisodeToAirDto(
    val air_date: String? = null,
    val episode_number: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    val production_code: String? = null,
    val season_number: Int? = null,
    val still_path: String? = null,
    val vote_average: Double?  = null,
    val vote_count: Int? = null
)