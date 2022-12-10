package com.mangata.tvshow_data.remote.dto.imagesResponse

import kotlinx.serialization.Serializable

@Serializable
data class PosterDto(
    val aspect_ratio: Double? = null,
    val file_path: String? = null,
    val height: Int? = null,
    val iso_639_1: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val width: Int? = null
)