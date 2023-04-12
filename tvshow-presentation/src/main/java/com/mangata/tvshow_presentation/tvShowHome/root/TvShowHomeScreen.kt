package com.mangata.tvshow_presentation.tvShowHome.root

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder
import com.mangata.core_ui.components.DefaultAlertDialog
import com.mangata.core_ui.theme.cardBackground
import com.mangata.core_ui.theme.textPrimary
import com.mangata.tvshow_presentation.tvShowHome.components.SearchTvShowCard
import com.mangata.tvshow_presentation.tvShowHome.components.TrendingSection
import com.mangata.tvshow_presentation.tvShowHome.components.WorkInProgressCard
import com.mangata.tvshow_presentation.tvShowHome.events.TvShowHomeEvents
import com.mangata.tvshow_presentation.tvShowHome.viewModel.TvShowHomeViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvShowHomeScreen(
    viewModel: TvShowHomeViewModel,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
    onSearchCardClick: () -> Unit,
) {
    val isLoading = viewModel.isLoading

    val pullRefreshState =
        rememberPullRefreshState(isLoading, { viewModel.onEvent(TvShowHomeEvents.Refresh) })

    if (viewModel.showAlert) {
        DefaultAlertDialog(
            title = "Seasons & Episodes",
            description = "We are working on the Episodes & Seasons tracking feature. It will be available in the next major version of the app!",
            confirmButtonText = "Dismiss",
            onDismissRequest = { viewModel.onEvent(TvShowHomeEvents.DismissAlertDialog) },
            onConfirmButtonClick = { viewModel.onEvent(TvShowHomeEvents.DismissAlertDialog) },
            modifier = Modifier.clip(MaterialTheme.shapes.small)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = "Trending Now",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.textPrimary
                )
            }
            item {
                TrendingSection(
                    items = viewModel.tvShowsState,
                    imageLoader = imageLoader,
                    onTvShowClick = onTvShowClick,
                    modifier = Modifier.placeholder(
                        visible = isLoading,
                        color = MaterialTheme.colors.cardBackground,
                        shape = MaterialTheme.shapes.medium,
                        highlight = PlaceholderHighlight.fade()
                    )
                )
            }
            item {
                SearchTvShowCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .placeholder(
                            visible = isLoading,
                            color = MaterialTheme.colors.cardBackground,
                            shape = MaterialTheme.shapes.medium,
                            highlight = PlaceholderHighlight.fade()
                        ),
                    onSearchCardClick = onSearchCardClick
                )
            }
            item {
                WorkInProgressCard(
                    modifier = Modifier.placeholder(
                        visible = isLoading,
                        color = MaterialTheme.colors.cardBackground,
                        shape = MaterialTheme.shapes.medium,
                        highlight = PlaceholderHighlight.fade()
                    ),
                    onInfoClick = { viewModel.onEvent(TvShowHomeEvents.ShowAlertDialog) })
            }
        }
        PullRefreshIndicator(isLoading, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }

}
