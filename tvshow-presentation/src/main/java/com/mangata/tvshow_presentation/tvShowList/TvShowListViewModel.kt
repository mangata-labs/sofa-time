package com.mangata.tvshow_presentation.tvShowList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.core_ui.pager.DefaultPager
import com.mangata.tvshow_domain.repository.TvShowRepository
import kotlinx.coroutines.launch

class TvShowListViewModel (private val tvShowRepository: TvShowRepository) : ViewModel() {

    private val _tvShowsState = mutableStateOf(TvShowListState())
    val tvShowsState: State<TvShowListState> = _tvShowsState

    private val pager = DefaultPager(
        initialKey = _tvShowsState.value.page,
        onLoadUpdated = {
            _tvShowsState.value = tvShowsState.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            tvShowRepository.getPopularTvShows(nextPage)
        },
        getNextKey = {
            _tvShowsState.value.page + 1
        },
        onError = {
            _tvShowsState.value = tvShowsState.value.copy(error = it?.localizedMessage ?: "")
        },
        onSuccess = { items, newKey ->
            _tvShowsState.value = tvShowsState.value.copy(
                tvShows = tvShowsState.value.tvShows + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextTvShows()
    }

    fun loadNextTvShows()  {
        viewModelScope.launch {
            pager.loadNext()
        }
    }
}