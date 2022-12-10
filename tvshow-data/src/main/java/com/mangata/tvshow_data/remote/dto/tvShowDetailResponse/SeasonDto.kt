package com.mangata.tvshow_data.remote.dto.tvShowDetailResponse

import kotlinx.serialization.Serializable

@Serializable
internal data class SeasonDto(
    val air_date: String? = null,
    val episode_count: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val season_number: Int? = null
)