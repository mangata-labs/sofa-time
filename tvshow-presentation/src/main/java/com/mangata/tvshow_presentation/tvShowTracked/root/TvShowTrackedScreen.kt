package com.mangata.tvshow_presentation.tvShowTracked.root

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.core_ui.theme.textPrimary
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.core_ui.components.EmptyListMessage
import com.mangata.tvshow_presentation.tvShowTracked.viewModel.TvShowTrackedViewModel
import com.mangata.core_ui.R as CoreUI

@Composable
fun TvShowTrackedScreen(
    imageLoader: ImageLoader,
    viewModel: TvShowTrackedViewModel,
    onTvDetailClick: (Int) -> Unit,
    onSettingsClick: () -> Unit
) {
    val trackedTvShows = viewModel.trackedTvShowsState
    val isLoading = viewModel.isLoadingState

    LaunchedEffect(Unit) {
        viewModel.getTrackedTvShow()
    }

    if (trackedTvShows.isEmpty() && !isLoading) {
        EmptyListMessage(message = "You are not tracking any Tv Show at the moment")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight()
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = (Alignment.CenterHorizontally)
        ) {
            Heading(
                modifier = Modifier.fillMaxWidth()
            ) { onSettingsClick() }
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(count = 2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(trackedTvShows) { tvShow ->
                    TvShowCell(
                        tvShow = tvShow,
                        imageLoader = imageLoader,
                        onTvDetailClick = onTvDetailClick
                    )
                }
            }
        }
    }
}

@Composable
private fun Heading(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Your Watchlist",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.textPrimary,
        )
        IconButton(
            onClick = { onSettingsClick() }
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
                tint = MaterialTheme.colors.textPrimary
            )
        }
    }
}

@Composable
private fun TvShowCell(
    tvShow: TvShow,
    imageLoader: ImageLoader,
    onTvDetailClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier.clickable { onTvDetailClick(tvShow.id) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(tvShow.posterPath)
                .placeholder(CoreUI.drawable.image_placeholder)
                .crossfade(true)
                .build(),
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
    }
}