package com.mangata.tvshow_presentation.tvShowList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.mangata.core_ui.components.DefaultSearchBar
import com.mangata.core_ui.util.loadMore
import com.mangata.tvshow_presentation.tvShowList.components.TvShowCard
import kotlinx.coroutines.flow.filter


@Composable
fun TvShowListScreen(
    modifier: Modifier = Modifier,
    viewModel: TvShowListViewModel,
    imageLoader: ImageLoader,
    onTvDetailClick: (Int) -> Unit,
) {
    val state = viewModel.tvShowsState.value
    val scrollState = rememberLazyListState()
    val loadMore by remember { derivedStateOf { scrollState.loadMore() } }

    LaunchedEffect(loadMore && !state.isLoading && !state.endReached) {
        snapshotFlow { loadMore }
            .filter { it }
            .collect {
                viewModel.loadNextTvShows()
            }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DefaultSearchBar(
            text = viewModel.searchState.value,
            placeholderText = "Search tv shows...",
            onTextChange = {
                viewModel.onEvent(TvShowListEvent.EnteredSearchText(it))
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = scrollState
        ) {
            items(state.tvShows) { tvShow ->
                TvShowCard(
                    tvShow = tvShow,
                    onTvDetailClick = onTvDetailClick,
                    imageLoader = imageLoader
                )
            }
            item {
                if (state.isLoading) {
                    Row {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}