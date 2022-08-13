package com.mangata.tvshow_presentation.tvSearch

sealed class TvShowSearchEvent {
    data class EnteredSearchText(val value: String) : TvShowSearchEvent()
    object FinishedSearch : TvShowSearchEvent()
}