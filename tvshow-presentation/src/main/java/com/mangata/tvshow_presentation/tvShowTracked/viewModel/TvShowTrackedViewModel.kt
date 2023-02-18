package com.mangata.tvshow_presentation.tvShowTracked.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_domain.repository.TvShowRepository
import kotlinx.coroutines.launch

class TvShowTrackedViewModel(
    private val repository: TvShowRepository
) : ViewModel() {

    var trackedTvShowsState by mutableStateOf<List<TvShow>>(emptyList())
        private set

    var isLoadingState by mutableStateOf(false)
        private set

    fun getTrackedTvShow() {
        isLoadingState = true
        viewModelScope.launch {
            val result = repository.getTrackedTvShows()
            trackedTvShowsState = result
            isLoadingState = false
        }
    }
}