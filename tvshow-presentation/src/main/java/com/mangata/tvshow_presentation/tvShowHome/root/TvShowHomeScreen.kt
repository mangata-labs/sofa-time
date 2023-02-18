package com.mangata.tvshow_presentation.tvShowHome.root

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.mangata.core_ui.components.ErrorMessage
import com.mangata.tvshow_presentation.tvShowHome.components.SearchTvShowCard
import com.mangata.tvshow_presentation.tvShowHome.components.TrendingSection
import com.mangata.tvshow_presentation.tvShowHome.components.WorkInProgressCard
import com.mangata.tvshow_presentation.tvShowHome.viewModel.TvShowHomeViewModel


@Composable
fun TvShowHomeScreen(
    viewModel: TvShowHomeViewModel,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
    onSearchCardClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    val trendingTvShows = viewModel.tvShowsState
    val error = viewModel.errorState
    val isLoading = viewModel.isLoading

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
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalAlignment = (Alignment.CenterHorizontally)
            ) {
                if (trendingTvShows.isNotEmpty()) {
                    TrendingSection(
                        items = viewModel.tvShowsState,
                        imageLoader = imageLoader,
                        onTvShowClick = onTvShowClick
                    )
                }
                SearchTvShowCard(
                    modifier = Modifier.fillMaxWidth(),
                    onSearchCardClick = onSearchCardClick
                )
                Spacer(Modifier.height(20.dp))
                WorkInProgressCard(onInfoClick = { onInfoClick() })
            }
        }
    }
}
