package com.mangata.tvshow_data.remote.dto.tvShowDetailResponse

import kotlinx.serialization.Serializable

@Serializable
internal data class GenreDto(
    val id: Int? = null,
    val name: String? = null,
)