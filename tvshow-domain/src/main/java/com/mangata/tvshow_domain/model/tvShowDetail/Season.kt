package com.mangata.tvshow_domain.model.tvShowDetail

data class Season(
    val id: Int,
    val airDate: String?,
    val episodeCount: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val seasonNumber: Int
)