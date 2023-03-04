package com.mangata.tvshow_presentation.tvShowHome.root

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.mangata.core_ui.components.ErrorMessage
import com.mangata.core_ui.theme.textPrimary
import com.mangata.tvshow_presentation.tvShowHome.components.SearchTvShowCard
import com.mangata.tvshow_presentation.tvShowHome.components.TrendingSection
import com.mangata.tvshow_presentation.tvShowHome.components.WorkInProgressCard
import com.mangata.tvshow_presentation.tvShowHome.events.TvShowHomeEvents
import com.mangata.tvshow_presentation.tvShowHome.viewModel.TvShowHomeViewModel


@Composable
fun TvShowHomeScreen(
    viewModel: TvShowHomeViewModel,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
    onSearchCardClick: () -> Unit,
) {

    if (viewModel.showAlert) {
        AlertDialog(
            modifier = Modifier.clip(MaterialTheme.shapes.small),
            onDismissRequest = {
                viewModel.onEvent(TvShowHomeEvents.DismissAlertDialog)
            },
            title = {
                Text(text = "Episodes & Seasons tracking")
            },
            text = {
                Text("Tracking Seasons & Episodes is under heavy construction. It will be available in the next major version of the app!")
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.onEvent(TvShowHomeEvents.DismissAlertDialog)
                }) {
                    Text("Dismiss")
                }
            },
        )
    }

    if (viewModel.errorState.isNotEmpty()) {
        ErrorMessage()
    }

    if (viewModel.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 20.dp),
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
                        onTvShowClick = onTvShowClick
                    )
                }
                item {
                    SearchTvShowCard(
                        modifier = Modifier.fillMaxWidth(),
                        onSearchCardClick = onSearchCardClick
                    )
                }
                item {
                    WorkInProgressCard(onInfoClick = {
                        viewModel.onEvent(TvShowHomeEvents.ShowAlertDialog)
                    })
                }
            }
        }
    }
}
