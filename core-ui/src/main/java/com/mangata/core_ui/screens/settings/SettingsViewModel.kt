package com.mangata.core_ui.screens.settings

import androidx.lifecycle.ViewModel
import com.mangata.core.util.AppConfiguration

class SettingsViewModel(
    private val appConfiguration: AppConfiguration
): ViewModel() {

    fun getAppVersion() = appConfiguration.VERSION_NAME
}