package com.mangata.tvshow_presentation.tvShowDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.core.util.awaitAll
import com.mangata.tvshow_domain.model.image.Poster
import com.mangata.tvshow_domain.model.tvShowDetail.TvShowDetails
import com.mangata.tvshow_domain.model.video.Video
import com.mangata.tvshow_domain.repository.TvShowRepository
import com.mangata.tvshow_presentation.tvShowDetail.components.headerSection.TvDetailsHeaderModel
import com.mangata.tvshow_presentation.util.toDetailHeaderModel
import kotlinx.coroutines.launch

class TvShowDetailViewModel(
    private val repository: TvShowRepository,
    private val tvShowId: Int
) : ViewModel() {

    var tvShowDetailState = mutableStateOf<TvShowDetails?>(null)
        private set

    var headerState = mutableStateOf(TvDetailsHeaderModel())
        private set

    var videoState = mutableStateOf<Video?>(null)
        private set

    var posterState = mutableStateOf<List<Poster>>(emptyList())
        private set

    var errorState = mutableStateOf("")
        private set

    var isLoading = mutableStateOf(false)
        private set

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch {
        isLoading.value = true
        try {
            awaitAll(
                launch { getTvShowDetails() },
                launch { getTvShowTrailer() },
                launch { getTvShowImages() },
            )
        } catch (e: Exception) {
            errorState.value = e.localizedMessage ?: ""
            isLoading.value = false
        }
        isLoading.value = false
    }

    private suspend fun getTvShowTrailer() {
        val result = repository.getVideoForTvShow(tvShowId)
        result.onSuccess { video ->
            videoState.value = video
        }
    }

    private suspend fun getTvShowImages() {
        val result = repository.getImagesForTvShow(tvShowId)
        result.onSuccess { posters ->
            posterState.value = posters
        }
    }

    private suspend fun getTvShowDetails() {
        val result = repository.getTvShowDetails(tvShowId)
        result.onSuccess { tvDetails ->
            tvDetails?.let {
                tvShowDetailState.value = it
                headerState.value = it.toDetailHeaderModel()
            }
        }
    }
}