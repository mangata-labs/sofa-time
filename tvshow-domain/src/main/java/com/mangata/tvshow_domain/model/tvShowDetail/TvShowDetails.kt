package com.mangata.tvshow_domain.model.tvShowDetail

import java.util.*

data class TvShowDetails(
    val id: Int,
    val name: String,
    val genres: List<Genre>,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val overview: String,
    val backdrop_path: String,
    val poster_path: String,
    val last_air_date: Date?,
    val first_air_date: Date?,
    val seasons: List<Season>,
    val episode_run_time: List<Int>,
    val homepage: String,
    val in_production: Boolean,
    val networks: List<Network>,
    val vote_average: Double,
    val vote_count: Int,
    val status: String,
)


