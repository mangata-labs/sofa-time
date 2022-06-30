package com.mangata.tvshow_presentation.tvShowDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.core.util.UiEvent
import com.mangata.tvshow_domain.repository.TvShowRepository
import com.mangata.tvshow_presentation.util.toDetailHeaderModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TvShowDetailViewModel(
    private val repository: TvShowRepository,
    private val tvShowId: Int
) : ViewModel() {

    var state = mutableStateOf(TvShowDetailState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getTvShowDetails()
        getTvShowTrailer()
        getTvShowImages()
    }


    private fun getTvShowTrailer() {
        viewModelScope.launch {
            val result = repository.getVideoForTvShow(tvShowId)
            result.onSuccess {
                state.value = state.value.copy(videoModel = it)
            }
        }
    }

    private fun getTvShowImages() {
        viewModelScope.launch {
            val result = repository.getImagesForTvShow(tvShowId)
            result.onSuccess {
                state.value = state.value.copy(imageModel = it)
            }
        }
    }

    private fun getTvShowDetails() {
        viewModelScope.launch {
            val result = repository.getTvShowDetails(tvShowId)
            result.onSuccess {
                state.value = state.value.copy(
                    tvShowDetails = it,
                    headerModel = it?.toDetailHeaderModel()
                )
            }
            result.onFailure {
                state.value = state.value.copy(error = it.localizedMessage ?: "")
            }
        }
    }
}