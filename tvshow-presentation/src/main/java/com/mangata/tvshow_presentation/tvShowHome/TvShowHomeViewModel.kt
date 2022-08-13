package com.mangata.tvshow_presentation.tvShowHome

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_domain.repository.TvShowRepository
import kotlinx.coroutines.launch

class TvShowHomeViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    var tvShowsState = mutableStateOf<List<TvShow>>(emptyList())
        private set

    var errorState = mutableStateOf("")
        private set

    var isLoading = mutableStateOf(false)
        private set

    init {
        loadTrendingTvShows()
    }

    private fun loadTrendingTvShows() {
        viewModelScope.launch {
            isLoading.value = true
            val result = tvShowRepository.getTrendingTvShows()
            result.onSuccess {
                tvShowsState.value = it
                isLoading.value = false
            }
            result.onFailure {
                errorState.value = it.localizedMessage ?: ""
                isLoading.value = false
            }
        }
    }
}