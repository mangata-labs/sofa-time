package com.mangata.tvshow_presentation.tvShowList

sealed class TvShowListEvent {
    data class EnteredSearchText(val value: String) : TvShowListEvent()
}