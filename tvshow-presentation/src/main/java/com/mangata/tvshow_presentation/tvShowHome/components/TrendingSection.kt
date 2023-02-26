package com.mangata.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.mangata.core_ui.theme.cardBackground
import com.mangata.core_ui.theme.textPrimary
import com.mangata.core_ui.theme.textPrimaryDim
import com.mangata.tvshow_domain.model.tvShowList.TvShow

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun TrendingSection(
    items: List<TvShow>,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
) {
    val pagerState = rememberPagerState()

    if (items.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(MaterialTheme.colors.surface, shape = MaterialTheme.shapes.medium)
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(40.dp, 40.dp),
                imageVector = Icons.Filled.WifiOff,
                tint = MaterialTheme.colors.primary,
                contentDescription = "No Internet Connection"
            )
            Text(
                text = "Unable To Load Tv Shows",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.textPrimary
            )
            Text(
                text = "Check your Internet Connection",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.textPrimaryDim
            )
        }
    } else {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
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
                inactiveColor = MaterialTheme.colors.cardBackground,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp, bottom = 10.dp)
            )
        }
    }
}
