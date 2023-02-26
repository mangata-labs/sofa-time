package com.mangata.tvshow_presentation.tvShowDetail.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangata.core_ui.util.UICoreEvent
import com.mangata.tvshow_domain.model.image.Poster
import com.mangata.tvshow_domain.model.tvShowDetail.TvShowDetails
import com.mangata.tvshow_domain.model.tvShowDetail.toTvShow
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_domain.model.video.Video
import com.mangata.tvshow_domain.repository.TvShowRepository
import com.mangata.tvshow_presentation.tvShowDetail.components.headingSection.TvDetailsHeadingModel
import com.mangata.tvshow_presentation.tvShowDetail.components.headingSection.toDetailHeaderModel
import com.mangata.tvshow_presentation.tvShowDetail.events.TvShowDetailEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class TvShowDetailViewModel(
    private val repository: TvShowRepository,
    private val tvShowId: Int
) : ViewModel() {

    var tvShowDetailState by mutableStateOf<TvShowDetails?>(null)
        private set

    var headerState by mutableStateOf(TvDetailsHeadingModel())
        private set

    var videoState by mutableStateOf<Video?>(null)
        private set

    var posterState by mutableStateOf<List<Poster>>(emptyList())
        private set

    var similarTvShowState by mutableStateOf<List<TvShow>>(emptyList())
        private set

    var errorState by mutableStateOf("")
        private set

    var isLoadingState by mutableStateOf(false)
        private set

    var isAddedToWatchList by mutableStateOf(false)
        private set

    private val eventChannel = Channel<UICoreEvent>(Channel.UNLIMITED)
    val eventsFlow = eventChannel.receiveAsFlow()

    init {
        getData()
    }

    fun onEvent(event: TvShowDetailEvent) {
        when (event) {
            is TvShowDetailEvent.AddedToWatchList -> addToWatchList()
            is TvShowDetailEvent.RemoveFromWatchlist -> deleteFromWatchlist()
        }
    }

    private fun addToWatchList() {
        viewModelScope.launch {
            tvShowDetailState?.let {
                launch(Dispatchers.IO) { repository.addTvShowToWatchList(it.toTvShow()) }
                eventChannel.send(UICoreEvent.SnackbarEvent(uiText = "Added to Watchlist"))
                isAddedToWatchList = true
            }
        }
    }

    private fun deleteFromWatchlist() {
        viewModelScope.launch {
            tvShowDetailState?.let {
                launch(Dispatchers.IO) { repository.removeTvShowFromWatchList(it.id) }
                eventChannel.send(UICoreEvent.SnackbarEvent(uiText = "Deleted from Watchlist"))
                isAddedToWatchList = false
            }
        }
    }

    private fun getData() = try {
        isLoadingState = true
        viewModelScope.launch {
            val tvShowDeferred = async { repository.getTvShowDetails(tvShowId) }
            val videoDeferred = async { repository.getVideoForTvShow(tvShowId) }
            val posterDeferred = async { repository.getImagesForTvShow(tvShowId) }
            val similarTvShowsDeferred = async { repository.getSimilarTvShows(tvShowId) }
            val watchlistDeferred = async { repository.getTrackedTvShows() }

            val tvShowDetailResult = tvShowDeferred.await()
            val videoResult = videoDeferred.await()
            val posterResult = posterDeferred.await()
            val watchList = watchlistDeferred.await()
            val similarTvShows = similarTvShowsDeferred.await()

            videoResult.onSuccess { processVideo(it) }
            posterResult.onSuccess { processPosters(it) }
            similarTvShows.onSuccess { processSimilarTvShows(it) }
            tvShowDetailResult.onSuccess { tvShow ->
                processTvShow(tvShow)
                isAddedToWatchList = handleWatchlistSelector(watchList, tvShow)
            }
            isLoadingState = false
        }
    } catch (e: Exception) {
        errorState = e.localizedMessage ?: ""
        isLoadingState = false
    }

    private fun processSimilarTvShows(tvShows: List<TvShow>) {
        similarTvShowState = tvShows
    }

    private fun processVideo(video: Video?) {
        videoState = video
    }

    private fun processPosters(posters: List<Poster>) {
        posterState = posters
    }

    private fun handleWatchlistSelector(
        watchList: List<TvShow>,
        remoteTvShow: TvShowDetails?
    ): Boolean {
        if (remoteTvShow == null) return false
        return watchList.find { it.id == remoteTvShow.id } != null
    }

    private fun processTvShow(tvShowDetails: TvShowDetails?) {
        if (tvShowDetails == null) {
            errorState = "Failed loading Tv Show"
            return
        }
        tvShowDetailState = tvShowDetails
        headerState = tvShowDetails.toDetailHeaderModel()
    }
}