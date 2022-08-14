package com.mangata.tvshow_presentation.tvShowDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.core_ui.components.ErrorMessage
import com.mangata.core_ui.util.UiEvent
import com.mangata.core_ui.util.observeWithLifecycle
import com.mangata.tvshow_presentation.R
import com.mangata.tvshow_presentation.tvShowDetail.components.headerSection.HeaderSection
import com.mangata.tvshow_presentation.tvShowDetail.components.networkSection.NetworkSection
import com.mangata.tvshow_presentation.tvShowDetail.components.storySection.StorySection
import com.mangata.tvshow_presentation.tvShowDetail.components.videoAndImageSection.VideoAndImageSection
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TvShowDetailScreen(
    imageLoader: ImageLoader,
    viewModel: TvShowDetailViewModel,
    scaffoldState: ScaffoldState,
    onNavigateToWebView: (String) -> Unit,
) {
    viewModel.eventsFlow.observeWithLifecycle {
        when(it) {
            is UiEvent.SnackbarEvent -> scaffoldState.snackbarHostState.showSnackbar(it.uiText)
        }
    }

    if (viewModel.errorState.value.isNotEmpty()) {
        ErrorMessage()
    }

    if (viewModel.isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    val tvShowState = viewModel.tvShowDetailState.value ?: return

    if (viewModel.didAllLoad.value) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tvShowState.backdropPath)
                        .placeholder(R.drawable.image_placeholder)
                        .crossfade(true)
                        .build(),
                    imageLoader = imageLoader,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                )
            }
            item {
                HeaderSection(
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
                VideoAndImageSection(
                    modifier = Modifier.padding(start = 20.dp),
                    imageLoader = imageLoader,
                    video = viewModel.videoState.value,
                    posters = viewModel.posterState.value,
                    onPlayVideoClick = { videoUrl ->
                        onNavigateToWebView(videoUrl)
                    }
                )
            }
            if (tvShowState.overview.isNotEmpty()) {
                item {
                    StorySection(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        story = tvShowState.overview
                    )
                }
            }
        }
    }
}

