package com.mangata.tvshow_presentation.di

import com.mangata.tvshow_presentation.tvShowDetail.TvShowDetailViewModel
import com.mangata.tvshow_presentation.tvShowList.TvShowListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvShowModule = module {
    viewModel { TvShowListViewModel(get()) }
    viewModel { params -> TvShowDetailViewModel(get(), tvShowId = params.get()) }
}