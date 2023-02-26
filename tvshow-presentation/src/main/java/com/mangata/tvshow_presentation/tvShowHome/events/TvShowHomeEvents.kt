package com.mangata.tvshow_presentation.tvShowHome.events

sealed class TvShowHomeEvents {
    object ShowAlertDialog : TvShowHomeEvents()
    object DismissAlertDialog : TvShowHomeEvents()
}