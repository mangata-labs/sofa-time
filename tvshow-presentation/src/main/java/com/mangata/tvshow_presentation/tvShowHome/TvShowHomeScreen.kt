package com.mangata.tvshow_presentation.tvShowHome

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.mangata.core_ui.components.ErrorMessage
import com.mangata.core_ui.theme.textPrimary
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_presentation.tvShowHome.components.SearchTvShowCard
import com.mangata.tvshow_presentation.tvShowHome.components.TrendingSection
import com.mangata.tvshow_presentation.tvShowHome.components.TvShowCarouselCard


@Composable
fun TvShowHomeScreen(
    viewModel: TvShowHomeViewModel,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
    onSearchCardLick: () -> Unit,
) {
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

    if(viewModel.tvShowsState.value.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = (Alignment.CenterHorizontally)
        ) {
            TrendingSection(
                modifier = Modifier.fillMaxWidth(0.9f),
                items = viewModel.tvShowsState.value,
                imageLoader = imageLoader,
                onTvShowClick = onTvShowClick
            )
            SearchTvShowCard(
                modifier = Modifier.fillMaxWidth(0.9f),
                onSearchCardLick = onSearchCardLick
            )
        }
    }
}