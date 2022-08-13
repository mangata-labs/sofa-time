package com.mangata.tvshow_presentation.tvShowDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.tvshow_domain.model.image.Poster
import com.mangata.tvshow_domain.model.tvShowDetail.TvShowDetails
import com.mangata.tvshow_domain.model.video.Video
import com.mangata.tvshow_domain.repository.TvShowRepository
import com.mangata.tvshow_presentation.tvShowDetail.components.headerSection.TvDetailsHeaderModel
import com.mangata.tvshow_presentation.tvShowDetail.components.headerSection.toDetailHeaderModel
import kotlinx.coroutines.*

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

    private fun getData() = try {
        isLoading.value = true
        viewModelScope.launch {
            val tvShowDeferred = async { repository.getTvShowDetails(tvShowId) }
            val videoDeferred = async { repository.getVideoForTvShow(tvShowId) }
            val posterDeferred = async{ repository.getImagesForTvShow(tvShowId) }

            val tvShowDetailResult = tvShowDeferred.await()
            val videoResult = videoDeferred.await()
            val posterResult = posterDeferred.await()

            tvShowDetailResult.onSuccess { processTvShow(it) }
            videoResult.onSuccess { processVideo(it) }
            posterResult.onSuccess { processPosters(it) }
            isLoading.value = false
        }
    } catch (e: Exception) {
        errorState.value = e.localizedMessage ?: ""
        isLoading.value = false
    }

    private fun processVideo(video: Video?) {
        videoState.value = video
    }

    private fun processPosters(posters: List<Poster>) {
        posterState.value = posters
    }

    private fun processTvShow(tvShowDetails: TvShowDetails?) {
        tvShowDetails?.let {
            tvShowDetailState.value = it
            headerState.value = it.toDetailHeaderModel()
        }
    }
}