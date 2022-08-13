package com.mangata.tvshow_presentation.di

import com.mangata.tvshow_presentation.tvShowDetail.TvShowDetailViewModel
import com.mangata.tvshow_presentation.tvShowSearch.TvShowSearchViewModel
import com.mangata.tvshow_presentation.tvShowHome.TvShowHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvShowModule = module {
    viewModel { TvShowSearchViewModel(get()) }
    viewModel { TvShowHomeViewModel(get()) }
    viewModel { params -> TvShowDetailViewModel(get(), tvShowId = params.get()) }
}