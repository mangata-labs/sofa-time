package com.mangata.tvshow_presentation.tvShowDetail.root

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.core_ui.components.ErrorMessage
import com.mangata.core_ui.util.UICoreEvent
import com.mangata.core_ui.util.observeWithLifecycle
import com.mangata.tvshow_presentation.tvShowDetail.components.headingSection.TvDetailsHeadingSection
import com.mangata.tvshow_presentation.tvShowDetail.components.mediaSection.MediaSection
import com.mangata.tvshow_presentation.tvShowDetail.components.networkSection.NetworkSection
import com.mangata.tvshow_presentation.tvShowDetail.components.similarTvSection.SimilarTvShowSection
import com.mangata.tvshow_presentation.tvShowDetail.components.storySection.StorySection
import com.mangata.tvshow_presentation.tvShowDetail.viewModel.TvShowDetailViewModel
import com.mangata.core_ui.R as CoreUI

@Composable
fun TvShowDetailScreen(
    imageLoader: ImageLoader,
    viewModel: TvShowDetailViewModel,
    scaffoldState: ScaffoldState,
    onNavigateToWebView: (String) -> Unit,
    onTvDetailClick: (Int) -> Unit
) {
    val isLoading = viewModel.isLoadingState
    val error = viewModel.errorState

    viewModel.eventsFlow.observeWithLifecycle {
        when (it) {
            is UICoreEvent.SnackbarEvent -> scaffoldState.snackbarHostState.showSnackbar(it.uiText)
        }
    }

    if (error.isNotEmpty()) {
        ErrorMessage()
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    val tvShowState = viewModel.tvShowDetailState ?: return

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tvShowState.backdropPath)
                    .placeholder(CoreUI.drawable.image_placeholder)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
        }
        item {
            TvDetailsHeadingSection(
                modifier = Modifier.padding(horizontal = 20.dp),
                viewModel = viewModel
            )
        }
        item {
            NetworkSection(
                modifier = Modifier.padding(start = 20.dp),
                imageLoader = imageLoader,
                networks = tvShowState.networks
            )
        }
        item {
            MediaSection(
                modifier = Modifier.padding(start = 20.dp),
                imageLoader = imageLoader,
                video = viewModel.videoState,
                posters = viewModel.posterState,
                onPlayVideoClick = { videoUrl ->
                    onNavigateToWebView(videoUrl)
                }
            )
        }
        item {
            StorySection(
                modifier = Modifier.padding(horizontal = 20.dp),
                story = tvShowState.overview
            )
        }
        if(viewModel.similarTvShowState.isNotEmpty()) {
            item {
                SimilarTvShowSection(
                    modifier = Modifier.padding(start = 20.dp),
                    imageLoader = imageLoader,
                    tvShows = viewModel.similarTvShowState,
                    onTvDetailClick = onTvDetailClick
                )
            }
        }
    }
}


