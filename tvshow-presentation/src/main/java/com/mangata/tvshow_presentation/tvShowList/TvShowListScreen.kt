package com.mangata.tvshow_presentation.tvShowList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.mangata.core_ui.util.loadMore
import com.mangata.tvshow_presentation.tvShowList.components.TvShowCard
import kotlinx.coroutines.flow.filter
import org.koin.androidx.compose.getViewModel


@Composable
fun TvShowListScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    viewModel: TvShowListViewModel,
    imageLoader: ImageLoader,
    onTvDetailClick: (Int) -> Unit,
) {
    val state = viewModel.tvShowsState.value
    val scrollState = rememberLazyListState()
    val loadMore by remember {
        derivedStateOf {
            scrollState.loadMore()
        }
    }
    val loadMoreCondition = loadMore && !state.isLoading && !state.endReached

    LaunchedEffect(loadMoreCondition) {
        snapshotFlow { loadMore }
            .filter { it }
            .collect {
                viewModel.loadNextTvShows()
            }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
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
                Row(modifier = modifier
                    .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}