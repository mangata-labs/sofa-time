package com.mangata.tvshow_presentation.tvShowList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.core_ui.pager.DefaultPager
import com.mangata.tvshow_domain.repository.TvShowRepository
import kotlinx.coroutines.launch

class TvShowListViewModel (private val tvShowRepository: TvShowRepository) : ViewModel() {

    var tvShowsState = mutableStateOf(TvShowListState())
        private set

    var searchState = mutableStateOf("")
        private set

    private val pager = DefaultPager(
        initialKey = tvShowsState.value.page,
        onLoadUpdated = {
            tvShowsState.value = tvShowsState.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            tvShowRepository.getPopularTvShows(nextPage)
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

    fun onEvent(event: TvShowListEvent) {
        when(event) {
            is TvShowListEvent.EnteredSearchText -> searchState.value = event.value
        }
    }

    fun loadNextTvShows()  {
        viewModelScope.launch {
            pager.loadNext()
        }
    }
}