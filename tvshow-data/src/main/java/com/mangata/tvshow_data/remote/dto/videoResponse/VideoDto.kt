package com.mangata.tvshow_data.remote.dto.videoResponse

import kotlinx.serialization.Serializable

@Serializable
data class PagedTrailerDto(
    val id: Int? = null,
    val results: List<TrailerDto> = emptyList()
)

@Serializable
data class TrailerDto(
    val id: String? = null,
    val iso_3166_1: String? = null,
    val iso_639_1: String? = null,
    val key: String? = null,
    val name: String? = null,
    val official: Boolean? = null,
    val published_at: String? = null,
    val site: String? = null,
    val size: Int? = null,
    val type: String? = null
)