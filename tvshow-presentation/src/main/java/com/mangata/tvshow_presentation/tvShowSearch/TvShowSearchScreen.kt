package com.mangata.tvshow_presentation.tvShowSearch

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
import com.mangata.tvshow_presentation.common.components.EmptyListMessage
import com.mangata.tvshow_presentation.tvShowSearch.components.SearchTvShowCard
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
@Composable
fun TvShowSearchScreen(
    viewModel: TvShowSearchViewModel,
    imageLoader: ImageLoader,
    onTvDetailClick: (Int) -> Unit,
) {
    val state = viewModel.tvShowsState.value
    val searchText = viewModel.searchState.collectAsState(initial = "")
    val scrollState = rememberLazyListState()
    val loadMore by remember { derivedStateOf { scrollState.loadMore() } }

    LaunchedEffect(loadMore && !state.isLoading && !state.endReached && state.error.isEmpty()) {
        snapshotFlow { loadMore }
            .filter { it }
            .collect {
                viewModel.loadNextTvShows()
            }
    }

    LaunchedEffect(true) {
        viewModel.searchState
            .drop(1)
            .debounce(500)
            .distinctUntilChanged()
            .collectLatest {
                viewModel.onEvent(TvShowSearchEvent.FinishedSearch)
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DefaultSearchBar(
            text = searchText.value,
            placeholderText = "Search tv shows...",
            onTextChange = {
                viewModel.onEvent(TvShowSearchEvent.EnteredSearchText(it))
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = scrollState
        ) {
            if (state.tvShows.isNotEmpty()) {
                items(state.tvShows) { tvShow ->
                    SearchTvShowCard(
                        tvShow = tvShow,
                        onTvDetailClick = onTvDetailClick,
                        imageLoader = imageLoader
                    )
                }
            }
            if (state.isLoading) {
                item {
                    Row {
                        CircularProgressIndicator()
                    }
                }
            }
            if (state.tvShows.isEmpty() && !state.isLoading) {
                item {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        EmptyListMessage(message = "Couldn't find any Tv Show")
                    }
                }
            }
        }
    }
}