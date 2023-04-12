package com.mangata.core_ui.screens.settings

import androidx.lifecycle.ViewModel
import com.mangata.core.util.AppConfiguration

class SettingsViewModel(
    private val appConfiguration: AppConfiguration
): ViewModel() {

    fun getAppVersion() = appConfiguration.VERSION_NAME
    fun getBuyMeACoffeeUrlPath() = "https://www.buymeacoffee.com/tomy1604"

    fun getTmdbUrl() = "https://www.themoviedb.org/"
}