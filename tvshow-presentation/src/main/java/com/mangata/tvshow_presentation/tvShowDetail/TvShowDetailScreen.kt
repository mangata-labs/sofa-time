package com.mangata.tvshow_presentation.tvShowDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.mangata.tvshow_presentation.R
import com.mangata.tvshow_presentation.tvShowDetail.components.headerSection.HeaderSection
import com.mangata.tvshow_presentation.tvShowDetail.components.networkSection.NetworkSection
import com.mangata.tvshow_presentation.tvShowDetail.components.storySection.StorySection
import com.mangata.tvshow_presentation.tvShowDetail.components.videoAndImageSection.VideoAndImageSection

@Composable
fun TvShowDetailScreen(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    viewModel: TvShowDetailViewModel,
    onNavigateToWebView: (String) -> Unit,
) {
    val state by viewModel.tvShowDetailState

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

    state?.let {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AsyncImage(
                    modifier = modifier.fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it.backdrop_path)
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
                    headerModel = viewModel.headerState.value
                )
            }
            item {
                NetworkSection(
                    modifier = Modifier.padding(start = 20.dp),
                    imageLoader = imageLoader,
                    networks = it.networks
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
            item {
                StorySection(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    story = it.overview
                )
            }
        }
    }
}
