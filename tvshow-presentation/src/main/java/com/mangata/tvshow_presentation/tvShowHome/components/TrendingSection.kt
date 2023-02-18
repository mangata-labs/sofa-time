package com.mangata.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.mangata.core_ui.theme.componentBackground
import com.mangata.core_ui.theme.textPrimary
import com.mangata.tvshow_domain.model.tvShowList.TvShow

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrendingSection(
    items: List<TvShow>,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Trending Now",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(15.dp))
        HorizontalPager(
            count = items.size,
            itemSpacing = 12.dp,
            state = pagerState
        ) { page ->
            TvShowCarouselCard(items[page], imageLoader, onTvShowClick)
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colors.primary,
            inactiveColor = MaterialTheme.colors.componentBackground,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
        )
    }
}