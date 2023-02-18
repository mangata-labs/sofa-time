package com.mangata.tvshow_presentation.tvShowHome.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_domain.repository.TvShowRepository
import kotlinx.coroutines.launch

class TvShowHomeViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    var tvShowsState by mutableStateOf<List<TvShow>>(emptyList())
        private set

    var errorState by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        loadTrendingTvShows()
    }

    private fun loadTrendingTvShows() {
        isLoading = true
        viewModelScope.launch {
            val result = tvShowRepository.getTrendingTvShows()
            result.onSuccess {
                tvShowsState = it
                isLoading = false
            }
            result.onFailure {
                errorState = it.localizedMessage ?: ""
                isLoading = false
            }
        }
    }
}