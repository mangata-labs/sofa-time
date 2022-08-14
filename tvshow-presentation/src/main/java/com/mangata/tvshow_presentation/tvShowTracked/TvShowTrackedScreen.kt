package com.mangata.tvshow_presentation.tvShowTracked

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import com.mangata.core_ui.theme.textPrimary
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_presentation.R
import com.mangata.tvshow_presentation.common.components.EmptySearchMessage

@Composable
fun TvShowTrackedScreen(
    imageLoader: ImageLoader,
    viewModel: TvShowTrackedViewModel,
    onTvDetailClick: (Int) -> Unit,
) {
    val state by viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.getTrackedTvShow()
    }

    if (state.isEmpty()) {
        EmptySearchMessage(message = "Couldn't find any Tv Show")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = (Alignment.CenterHorizontally)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "Your Watchlist",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.textPrimary,
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state) { tvShow ->
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
fun TvShowCell(
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
                .placeholder(R.drawable.image_placeholder)
                .crossfade(true)
                .build(),
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
    }
}