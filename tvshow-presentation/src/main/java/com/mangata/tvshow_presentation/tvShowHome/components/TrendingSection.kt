package com.mangata.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.mangata.core_ui.theme.cardBackground
import com.mangata.core_ui.theme.textPrimary
import com.mangata.core_ui.theme.textPrimaryDim
import com.mangata.core_ui.util.shake
import com.mangata.tvshow_domain.model.tvShowList.TvShow

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TrendingSection(
    items: List<TvShow>,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    if (items.isEmpty()) {
        ErrorPlaceholder(
            modifier = modifier
        )
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                pageCount = items.size,
                pageSpacing = 12.dp,
                state = pagerState
            ) { page ->
                TvShowCarouselCard(
                    tvShow = items[page],
                    imageLoader = imageLoader,
                    onTvShowClick = onTvShowClick,
                    modifier = modifier
                )
            }
            PagerIndicator(
                itemCount = items.size,
                pagerState = pagerState,
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagerIndicator(
    itemCount: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(itemCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colors.primary else MaterialTheme.colors.cardBackground
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
            )
        }
    }
}

@Composable
fun ErrorPlaceholder(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .shadow(1.5.dp, shape = MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.surface, shape = MaterialTheme.shapes.medium)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp, 40.dp)
                .shake(),
            imageVector = Icons.Filled.WifiOff,
            tint = MaterialTheme.colors.primary,
            contentDescription = "No Internet Connection"
        )
        Text(
            text = "Unable to load Tv Shows",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.textPrimary
        )
        Text(
            text = "Check your Internet Connection",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.textPrimaryDim
        )
    }
}
