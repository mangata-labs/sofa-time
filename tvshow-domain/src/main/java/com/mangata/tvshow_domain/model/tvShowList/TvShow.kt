package com.mangata.tvshow_domain.model.tvShowList

import com.mangata.tvshow_domain.model.tvShowDetail.Genre

data class TvShow(
    val id: Int,
    val name: String,
    val genreIDs: List<Int>,
    val description: String,
    val posterPath: String,
    val backdropPath: String,
    val popularity: Double,
    val voteAverage: Double,
)