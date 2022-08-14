package com.mangata.tvshow_presentation.tvShowTracked

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_domain.repository.TvShowRepository
import kotlinx.coroutines.launch

class TvShowTrackedViewModel(
    private val repository: TvShowRepository
) : ViewModel() {

    var state = mutableStateOf<List<TvShow>>(emptyList())
        private set

    fun getTrackedTvShow() {
        viewModelScope.launch {
            val result = repository.getTrackedTvShows()
            state.value = result
        }
    }
}