package com.mangata.tvshow_presentation.tvShowSearch.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.core_ui.pager.DefaultPager
import com.mangata.tvshow_domain.repository.TvShowRepository
import com.mangata.tvshow_presentation.tvShowSearch.events.TvShowSearchEvent
import com.mangata.tvshow_presentation.tvShowSearch.state.TvShowListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TvShowSearchViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    var tvShowsState by mutableStateOf(TvShowListState())
        private set

    private var _searchState = MutableStateFlow("")
    val searchState = _searchState.asStateFlow()

    private val pager = DefaultPager(
        initialKey = tvShowsState.page,
        onLoadUpdated = {
            tvShowsState = tvShowsState.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            if (_searchState.value.isNotEmpty()) {
                tvShowRepository.searchTvShows(_searchState.value, nextPage)
            } else {
                tvShowRepository.getPopularTvShows(nextPage)
            }
        },
        getNextKey = {
            tvShowsState.page + 1
        },
        onError = {
            tvShowsState = tvShowsState.copy(error = it?.localizedMessage ?: "")
        },
        onSuccess = { items, newKey ->
            tvShowsState = tvShowsState.copy(
                tvShows = tvShowsState.tvShows + items,
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
            is TvShowSearchEvent.OnSearchTextChanged -> _searchState.value = event.text
            is TvShowSearchEvent.OnSearchFinished -> searchTvShows()
        }
    }

    private fun searchTvShows() {
        viewModelScope.launch {
            pager.reset()
            tvShowsState = TvShowListState()
            pager.loadNext()
        }
    }

    fun loadNextTvShows() {
        viewModelScope.launch {
            pager.loadNext()
        }
    }
}