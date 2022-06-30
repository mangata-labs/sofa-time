package com.mangata.tvshow_presentation.tvShowDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
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
import com.mangata.core.util.UiEvent
import com.mangata.tvshow_presentation.R
import com.mangata.tvshow_presentation.tvShowDetail.components.HeaderSection
import com.mangata.tvshow_presentation.tvShowDetail.components.NetworkSection
import com.mangata.tvshow_presentation.tvShowDetail.components.StorySection
import com.mangata.tvshow_presentation.tvShowDetail.components.VideoAndImageSection
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TvShowDetailScreen(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    viewModel: TvShowDetailViewModel,
    onNavigateToWebView: (String) -> Unit,
) {
    val state by viewModel.state

    if (state.error.isNotEmpty()) {
        Text(
            modifier = modifier.wrapContentSize(Alignment.Center),
            text = "Error when loading content!")
    }

    state.tvShowDetails?.let {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.tvShowDetails?.backdrop_path)
                    .placeholder(R.drawable.image_placeholder)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                HeaderSection(
                    modifier = modifier,
                    headerModel = state.headerModel
                )
                NetworkSection(
                    modifier = modifier,
                    imageLoader = imageLoader,
                    networks = it.networks
                )
                VideoAndImageSection(
                    modifier = modifier,
                    imageLoader = imageLoader,
                    videoModel = state.videoModel,
                    posterModel = state.imageModel,
                    onPlayVideoClick = { videoUrl ->
                        onNavigateToWebView(videoUrl)
                    }
                )
                StorySection(
                    modifier = modifier,
                    story = it.overview
                )
            }
        }
    }
}