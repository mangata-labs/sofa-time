package com.mangata.tvshow_presentation.tvShowSearch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.core_ui.pager.DefaultPager
import com.mangata.tvshow_domain.repository.TvShowRepository
import com.mangata.tvshow_presentation.common.state.TvShowListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TvShowSearchViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    var tvShowsState = mutableStateOf(TvShowListState())
        private set

    var searchState = MutableStateFlow("")
        private set

    private val pager = DefaultPager(
        initialKey = tvShowsState.value.page,
        onLoadUpdated = {
            tvShowsState.value = tvShowsState.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            if (searchState.value.isNotEmpty()) {
                tvShowRepository.searchTvShows(searchState.value, nextPage)
            } else {
                tvShowRepository.getPopularTvShows(nextPage)
            }
        },
        getNextKey = {
            tvShowsState.value.page + 1
        },
        onError = {
            tvShowsState.value = tvShowsState.value.copy(error = it?.localizedMessage ?: "")
        },
        onSuccess = { items, newKey ->
            tvShowsState.value = tvShowsState.value.copy(
                tvShows = tvShowsState.value.tvShows + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextTvShows()
    }

    fun onEvent(event: TvShowSearchEvent) {
        when (event) {
            is TvShowSearchEvent.EnteredSearchText -> searchState.value = event.value
            is TvShowSearchEvent.FinishedSearch -> searchTvShows()
        }
    }

    private fun searchTvShows() {
        viewModelScope.launch {
            pager.reset()
            tvShowsState.value = TvShowListState()
            pager.loadNext()
        }
    }

    fun loadNextTvShows() {
        viewModelScope.launch {
            pager.loadNext()
        }
    }
}