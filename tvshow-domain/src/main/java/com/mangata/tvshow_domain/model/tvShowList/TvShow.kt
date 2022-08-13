package com.mangata.tvshow_domain.model.tvShowList


data class TvShow(
    val id: Int,
    val name: String,
    val genreIDs: List<Int>,
    val description: String?,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
)