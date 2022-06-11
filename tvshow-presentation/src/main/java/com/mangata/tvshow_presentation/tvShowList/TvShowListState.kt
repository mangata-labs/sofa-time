package com.mangata.tvshow_presentation.tvShowList

import com.mangata.tvshow_domain.model.TvShow

data class TvShowListState(
    val tvShows: List<TvShow> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val page: Int = 1,
)