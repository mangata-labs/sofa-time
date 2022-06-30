package com.mangata.tvshow_presentation.tvShowDetail

sealed class TvShowDetailEvent {
    data class PlayVideoClicked(val videoUrl: String) : TvShowDetailEvent()
}