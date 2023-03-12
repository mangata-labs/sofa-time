package com.mangata.core_ui.di

import com.mangata.core_ui.screens.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val coreUIModule = module {
    viewModel { SettingsViewModel(get()) }
}