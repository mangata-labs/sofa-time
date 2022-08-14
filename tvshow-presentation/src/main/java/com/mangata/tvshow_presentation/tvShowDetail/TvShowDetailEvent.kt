package com.mangata.tvshow_presentation.tvShowDetail

sealed class TvShowDetailEvent() {
    object AddedToWatchList : TvShowDetailEvent()
    object RemoveFromWatchlist : TvShowDetailEvent()
}