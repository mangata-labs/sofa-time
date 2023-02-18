package com.mangata.tvshow_presentation.di

import com.mangata.tvshow_presentation.tvShowDetail.viewModel.TvShowDetailViewModel
import com.mangata.tvshow_presentation.tvShowSearch.viewModel.TvShowSearchViewModel
import com.mangata.tvshow_presentation.tvShowHome.viewModel.TvShowHomeViewModel
import com.mangata.tvshow_presentation.tvShowTracked.viewModel.TvShowTrackedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvShowModule = module {
    viewModel { TvShowSearchViewModel(get()) }
    viewModel { TvShowHomeViewModel(get()) }
    viewModel { params -> TvShowDetailViewModel(get(), tvShowId = params.get()) }
    viewModel { TvShowTrackedViewModel(get()) }
}